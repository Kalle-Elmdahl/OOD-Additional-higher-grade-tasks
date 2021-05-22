package se.kth.iv1350.seminar4.DTO;

/**
 * DiscountDTO This class is used for sending discounts throughout the program
 */
public class DiscountDTO {
    private String type;
    private String idOfDiscountedItem;
    private double amount;
    private double minRequiredPrice;

    /**
     * This function creates a new instance of the discountDTO in this case the discount is of the type item and
     * should be used for discounting specific items
     * @param idOfDiscountedItem The identifier of the targeted item
     * @param amount The amount the item is disounted if amount < 1 it is read as a discount percentage, 
     * otherwise it is read as an amount to subtract from the original price
     */
    public DiscountDTO(String idOfDiscountedItem, double amount) {
        type = "item";
        this.idOfDiscountedItem = idOfDiscountedItem;
        this.amount = amount;
    }

    /**
     * This function creates a new instance of the discountDTO class this constructor creates a sale discount 
     * which should be used for applying to an sale
     * @param amount The amount the item is disounted if amount < 1 it is read as a discount percentage, 
     * otherwise it is read as an amount to subtract from the original price
     * @param minRequiredPrice This is the lowest price of the sale where the discount is activated
     */
    public DiscountDTO(double amount, double minRequiredPrice) {
        type = "sale";
        this.amount = amount;
        this.minRequiredPrice = minRequiredPrice;
    }

    /**
     * Gets the type of discount. Either "item" or "sale"
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the identifier of the discounted item (only used for item discounts)
     * @return the identifier
     */
    public String getIdOfDiscountedItem() {
        return this.idOfDiscountedItem;
    }

    /**
     * Gets how much the discount should reduce the price if amount is < 1 it should be read as a percentage
     * @return the amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Gets the minimum required sale price for the discount to be valid
     * @return the minimum price
     */
    public double getMinRequiredPrice() {
        return this.minRequiredPrice;
    }
}