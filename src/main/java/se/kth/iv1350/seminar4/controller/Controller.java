package se.kth.iv1350.seminar4.controller;

import se.kth.iv1350.seminar4.model.Sale;
import se.kth.iv1350.seminar4.model.Receipt;
import se.kth.iv1350.seminar4.model.Register;
import se.kth.iv1350.seminar4.model.SaleObserver;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.seminar4.DTO.*;
import se.kth.iv1350.seminar4.discount.ItemDiscount;
import se.kth.iv1350.seminar4.discount.SaleDiscount;
import se.kth.iv1350.seminar4.integration.*;

/**
 * This is the application's only controller. All calls through the model pass through this class.
 */
public class Controller {
    private Sale sale;
    private EISHandler eis;
    private EASHandler eas;
    private Printer printer;
    private DCHandler dc;
    private Register register;

    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * This function generates a new instance of the controller
     * @param eis an external inventory handler
     * @param eas an external account system
     * @param printer a printer for printing receipts
     */

    public Controller(EISHandler eis, EASHandler eas, Printer printer, DCHandler dc) {
        this.eis = eis;
        this.eas = eas;
        this.printer = printer;
        this.dc = dc;

        this.register = Register.getInstance();

        System.out.println("Controller was started successfully");
    }

    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     */
    public void startSale() {
        sale = new Sale();
        for(SaleObserver obs : saleObservers)
            sale.addSaleObserver(obs);
    }
    
    /** 
     * Adds an item to a sale.
     * @param identifier The item's identifier. This must be valid. Invalid identifiers are not handled
     * @return SaleInfoDTO Information to be shown on the screen in the view
     * @throws ItemNotFoundException When the scanned identifier is invalid 
     * @throws ServerDownException When the external server is down
     */
    public SaleInfoDTO enterItem(String identifier) throws ItemNotFoundException, ServerDownException {
        if(sale.isDuplicate(identifier))
            return sale.addDuplicate(identifier);
        try {
            ItemDTO item = eis.findItem(identifier);
            return sale.addItem(item);
        } catch (ItemNotFoundException | ServerDownException exc) {
            System.out.println("[FOR DEVELOPER]: " + exc.getMessage());
            throw exc;
        }
    }

    /**
     * This function adds discounts based on what the customer has purchased
     * @return double the newly updated running total
     */

    public double applyDiscounts() {
        SaleDTO saleDTO = sale.convertToDTO();
        List<DiscountDTO> itemDiscounts = dc.findDiscounts(saleDTO, new ItemDiscount());
        List<DiscountDTO> saleDiscounts = dc.findDiscounts(saleDTO, new SaleDiscount());
        System.out.println("[LOG]: Found " + itemDiscounts.size() + " item discounts and " + saleDiscounts.size() + " sale discounts");

        sale.applyItemDiscounts(itemDiscounts);
        sale.applyDiscounts(saleDiscounts);
        return sale.getTotalPrice();
    }

    
    /** 
     * Handles a payment. This function also completes the sale and updates external systems.
     * @param amount the amount the customer has paid
     * @param currency the currency the customer paid in.
     * @return double the amount of change the cashier should give.
     */
    public double pay(double amount, String currency) {
        PaymentDTO payment = new PaymentDTO(amount, currency);
        SaleDTO sale = this.sale.convertToDTO();

        handleCompleteSale(payment, sale);
        
        double change = payment.getAmount() - sale.getTotalPrice();
        return change;
    }

    private void handleCompleteSale(PaymentDTO payment, SaleDTO sale) {
        Receipt receipt = this.sale.complete(payment, sale);

        register.updateAmount(payment.getAmount());

        eas.registerPayment(payment, sale);
        eis.updateInventory(sale);

        printer.printReceipt(receipt);

        this.sale = null;
    }
    

    /**
     * The specified observer will be notified when a rental has been paid. There will be
     * notifications only for rentals that are started after this method is called.
     *
     * @param obs The observer to notify.
     */
    public void addSaleObserver(SaleObserver obs) {
        saleObservers.add(obs);
    }
}