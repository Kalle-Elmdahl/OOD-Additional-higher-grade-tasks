package se.kth.iv1350.seminar4.discount;

import java.util.List;

import se.kth.iv1350.seminar4.DTO.*;

/**
 * DiscountFinder an interface used to find discounts this can be used when the customer 
 * wants discounts on a sale. Different implementattions of the interface can find discounts in different ways
 */
public interface DiscountFinder {
    /**
     * findDiscount finds valid discounts based on a few parameters
     * @param saleDTO the sale to use for finding discounts
     * @param availableDiscounts the store's available discounts
     * @return The valid discounts that can be added to the sale 
     */
    List<DiscountDTO> findDiscount(SaleDTO saleDTO, List<DiscountDTO> availableDiscounts);
}