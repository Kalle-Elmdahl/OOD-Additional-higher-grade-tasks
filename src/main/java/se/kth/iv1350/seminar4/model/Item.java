package se.kth.iv1350.seminar4.model;

import se.kth.iv1350.seminar4.DTO.DiscountDTO;
import se.kth.iv1350.seminar4.DTO.ItemDTO;

/**
 * This class is used for storing items the customer has bought.
 */
public class Item {
    String name;
    double VAT;
    String description;
    double price;
    int quantity;
    String identifier;

    /**
     * This function creates a new instance of the Item class.
     * @param item the item DTO to be converted.
     */
    public Item(ItemDTO item) {
        description = item.getDescription();
        VAT = item.getVAT();
        price = item.getPrice();
        name = item.getName();
        identifier = item.getIdentifier();
        quantity = 1;
    }

    /**
     * This is a secondary constructor used for creating copies of an item.
     * @param item the item to be copied.
     */
    public Item(Item item) {
        description = item.getDescription();
        VAT = item.getVAT();
        price = item.getPrice();
        name = item.getName();
        identifier = item.getIdentifier();
        quantity = item.getQuantity();
    }

    /**
     * updates the price of the item based on a discount
     * @param discount the discount to be added to the item's price
     */
    public void applyDiscount(DiscountDTO discount) {
        price = discount.getAmount() < 1 ? price * 1 - discount.getAmount() : price - discount.getAmount();
    }

    
    /** 
     * This function returns the name of the item
     * @return String the name
     */
    public String getName() {
        return this.name;
    }

    
    /** 
     * This function returns the VAT of the item
     * @return double the VAT
     */
    public double getVAT() {
        return this.VAT;
    }

    
    /** 
     * This function returns the description of the item
     * @return String the description
     */
    public String getDescription() {
        return this.description;
    }

    
    /** 
     * This function returns the price of the item
     * @return double the price
     */
    public double getPrice() {
        return this.price;
    }

    
    /** 
     * This function returns the quantity of the item
     * @return int the quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Increses the quantity of the item 
     */
    public void increaseQuantity() {
        quantity += 1;

    }

    /** 
     * This function returns the identifier of the item
     * @return String the identifier
     */
    public String getIdentifier() {
        return this.identifier;
    }
}
