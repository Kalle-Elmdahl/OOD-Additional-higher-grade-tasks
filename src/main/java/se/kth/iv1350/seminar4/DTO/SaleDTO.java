package se.kth.iv1350.seminar4.DTO;

import java.time.LocalTime;
import java.util.ArrayList;

import se.kth.iv1350.seminar4.model.Item;

/**
 * This is a data transfer object class used to send the sale to external systems.
 */
public class SaleDTO {
    LocalTime time;
    private ArrayList<Item> items;
    double totalPrice;
    double totalVAT;

    /**
     * This function creates a new instance of a sale data transfer object
     * @param time the time when the sale was started
     * @param items all the items of the sale
     * @param totalPrice the total price of all the items
     * @param totalVAT the total VAT of all the items.
     */
    public SaleDTO(LocalTime time, ArrayList<Item> items, double totalPrice, double totalVAT) {
        this.time = time;
        this.items = items;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
    }

    
    /** 
     * This function gets the time when the sale was started
     * @return LocalTime the time in java.time.LocalTiem format
     */
    public LocalTime getTime() {
        return this.time;
    }

    
    /** 
     * This function gets all the scanned items of the sale
     * @return ArrayList<Item> the items
     */
    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        for(Item item : this.items)
            items.add(new Item(item));
        return items;
    }
    
    /** 
     * This function gets the total price of all the scanned items
     * @return double the price
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    
    /** 
     * This function gets the total VAT of all the scanned items
     * @return double the total VAT
     */
    public double getTotalVAT() {
        return this.totalVAT;
    }
}