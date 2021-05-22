package se.kth.iv1350.seminar4.DTO;


/**
 * This is a data transfer object class used to send items.
 */
public class ItemDTO {
    String description;
    double VAT;
    double price;
    String name;
    String identifier;

    /**
     * This function creates a new instance of an item data transfer object
     * @param description A description of the item
     * @param VAT The item's VAT
     * @param price The pirce of the item
     * @param name The item's name
     * @param identifier The item's identifier
     */
    public ItemDTO(String description, double VAT, double price, String name, String identifier) {
        this.description = description;
        this.VAT = VAT;
        this.price = price;
        this.name = name;
        this.identifier = identifier;
    }


    /** 
     * This function returns the item's description
     * @return String the description
     */
    public String getDescription() {
        return this.description;
    }

    
    /** 
     * This function returns the item's VAT
     * @return double the VAT
     */
    public double getVAT() {
        return this.VAT;
    }

    
    /** 
     * This function returns the item's price
     * @return double the price
     */
    public double getPrice() {
        return this.price;
    }

    
    /** 
     * This function returns the item's name
     * @return String the name
     */
    public String getName() {
        return this.name;
    }

    
    /** 
     * This function returns the item's identifier
     * @return String the identifier
     */
    public String getIdentifier() {
        return this.identifier;
    }
}
