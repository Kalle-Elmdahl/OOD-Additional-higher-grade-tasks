package se.kth.iv1350.seminar4.model;

import java.time.LocalTime;
import java.util.ArrayList;

import se.kth.iv1350.seminar4.DTO.*;

/**
 * Represents one receipt, which proves the payment of one sale
 */
public class Receipt {
    LocalTime timeOfSale;
    private ArrayList<Item> items = new ArrayList<Item>();
    private double totalPrice;
    private double totalVAT;
    private double amountPaid;
    private String currency;
    private String storeInformation = "ICA Brottbyhallen. Handla online på ICA.se - Här finns nya fantastiska erbjudanden varje dag!";

    /**
     * Genreates a new instance of the receipt
     * @param payment The customer's payment
     * @param sale The customer's sale
     */
    public Receipt(PaymentDTO payment, SaleDTO sale) {
        this.timeOfSale = sale.getTime();
        this.items = sale.getItems();
        this.totalPrice = sale.getTotalPrice();
        this.totalVAT = sale.getTotalVAT();

        this.amountPaid = payment.getAmount();
        this.currency = payment.getCurrency();
    }


    
    /** 
     * This function returns the time when the sale was started.
     * @return LocalTime, The time
     */
    public LocalTime getTime() {
        return this.timeOfSale;
    }

    
    /** 
     * Gets the purchased items in the sale
     * @return ArrayList<Item> The items
     */
    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        for(Item item : this.items)
            items.add(new Item(item));
        return items;
    }

    
    /** 
     * Gets the totalprice
     * @return double the price
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    
    /** 
     * This function returns the totalVAT.
     * @return double The totalVAT
     */
    public double getTotalVAT() {
        return this.totalVAT;
    }

    
    /** 
     * This function returns the amountPaid.
     * @return double The amountPaid
     */
    public double getAmountPaid() {
        return this.amountPaid;
    }

    
    /** 
     * This function returns the currency.
     * @return String The currency
     */
    public String getCurrency() {
        return this.currency;
    }

    
    /** 
     * Gets Information about the store
     * @return String the information
     */
    public String getStoreInformation() {
        return this.storeInformation;
    }    
}
