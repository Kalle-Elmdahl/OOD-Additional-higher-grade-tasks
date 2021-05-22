package se.kth.iv1350.seminar4.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.seminar4.DTO.*;

/**
 * One single sale made by one single customer and payed with one payment.
 */
public class Sale {
    private LocalTime saleTime;
    private ArrayList<Item> items;
    private double totalPrice;
    private double totalVAT;
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Crates a new instance and saves the time of the sale
     */
    public Sale() {
        saleTime = LocalTime.now();
        items = new ArrayList<Item>();
    }

    private void updateRunningTotal() {
        double runningTotal = 0;
        double totalVAT = 0;
        for(Item item : items) {
            runningTotal += item.getPrice() * item.getQuantity();
            totalVAT += item.getVAT() * item.getQuantity();
        }
        this.totalPrice = runningTotal;
        this.totalVAT = totalVAT;
    }

    
    /** 
     * This function increases the quantity of a matching identifier
     * @param idenfier the identifier to be matched
     * @return SaleInfoDTO Information about the sale to be sent back to the view.
     */
    public SaleInfoDTO addDuplicate(String idenfier) {
        for(Item item : items) {
            if(item.getIdentifier().equals(idenfier)) {
                item.increaseQuantity();
                updateRunningTotal();
                return new SaleInfoDTO(item, this.totalPrice);
            }
        }
        return null;
    }

    
    /** 
     * This function adds a new item to the sale's item list
     * @param itemDTO the item to be added
     * @return SaleInfoDTO information to be shown in th view.
     */
    public SaleInfoDTO addItem(ItemDTO itemDTO) {
        Item item = new Item(itemDTO);
        items.add(item);
        updateRunningTotal();

        return new SaleInfoDTO(item, this.totalPrice);
    }

    
    /** 
     * This function checks if an item with the same identifier already exists in the scanned items list
     * @param idenfier the itentifier to be matched
     * @return boolean true if match was found
     */
    public boolean isDuplicate(String idenfier) {
        for(Item item : items)
            if(item.getIdentifier().equals(idenfier))
                return true;
    
        return false;
    }

    
    /** 
     * This function converts the sale to a SaleDTO to be sent to external systems
     * @return SaleDTO the DTO version of the sale
     */
    public SaleDTO convertToDTO() {
        return new SaleDTO(this.saleTime, this.items, this.totalPrice, this.totalVAT);
    }

    /**
     * Updates the sale's item prices based on a number of discounts
     * @param discounts the valid discounts to be added to the sale's items
     */
    public void applyItemDiscounts(List<DiscountDTO> discounts) {
        for(DiscountDTO discount : discounts) {
            for(Item item : items) {
                if(item.getIdentifier().equals(discount.getIdOfDiscountedItem()))
                    item.applyDiscount(discount);
            }
        }
        updateRunningTotal();
    }

    /**
     * Updates the sale's total price based on a number of discounts
     * @param discounts the valid discounts to be added to the sale's price
     */
    public void applyDiscounts(List<DiscountDTO> discounts) {
        for (DiscountDTO discount : discounts)
            totalPrice = discount.getAmount() < 1 ? totalPrice * 1 - discount.getAmount() : totalPrice - discount.getAmount();
    
    }

    
    /** 
     * This function completes the sale
     * @param payment the payment made by the customer
     * @param sale the sale 
     * @return Receipt the generated receipt
     */
    public Receipt complete(PaymentDTO payment, SaleDTO sale) {
        notifyObservers();
        return new Receipt(payment, sale);
    }

    private void notifyObservers() {
        for(SaleObserver obs : saleObservers)
            obs.newSale(totalPrice);
    }

    /**
     * Adds an observer. The observer will be notified when sale is completed
     * 
     * @param obs The observer to notify. 
     */
    public void addSaleObserver(SaleObserver obs) {
        saleObservers.add(obs);
    }

    
    /** 
     * Gets the items scanned in the sale
     * @return ArrayList<Item> the items
     */
    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        for(Item item : this.items)
            items.add(new Item(item));
        return items;
    }

    
    /** 
     * Gets the total price of the sale
     * @return double total price
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    
    /** 
     * Gets the total VAT of the sale
     * @return double the total VAT
     */
    public double getTotalVAT() {
        return this.totalVAT;
    }
}
