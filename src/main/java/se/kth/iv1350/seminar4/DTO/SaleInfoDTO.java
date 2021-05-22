package se.kth.iv1350.seminar4.DTO;

import se.kth.iv1350.seminar4.model.Item;

/**
 * This is a data transfer object used for transfering information about the lastest scanned item
 */
public class SaleInfoDTO {
    String currentItemDescription;
    double currentItemPrice;
    String currentItemName;
    int currentItemQuantity;
    double runningTotal;
    
    /**
     * This function creates a new instance of a sale info data transfer object
     * @param item The item that was scanned last
     * @param runningTotal the total price of all the scanned item
     */
    public SaleInfoDTO(Item item, double runningTotal) {
        currentItemDescription = item.getDescription();
        currentItemPrice = item.getPrice();
        currentItemName = item.getName();
        currentItemQuantity = item.getQuantity();
        this.runningTotal = runningTotal;
    }

    
    /** 
     * This function gets the description of the last scanned item
     * @return String the description
     */
    public String getCurrentItemDescription() {
        return this.currentItemDescription;
    }

    
    /** 
     * This function gets the price of the last scanned item
     * @return double the price
     */
    public double getCurrentItemPrice() {
        return this.currentItemPrice;
    }

    
    /** 
     * This function gets the name of the last scanned item
     * @return String the name
     */
    public String getCurrentItemName() {
        return this.currentItemName;
    }

    
    /** 
     * This function gets the quantity of the last scanned item
     * @return int the quantity
     */
    public int getCurrentItemQuantity() {
        return this.currentItemQuantity;
    }

    /**
     * This function gets te total price of all the scanned items
     * @return double the running total
     */
    public double getRunningTotal() {
        return this.runningTotal;
    }
}
