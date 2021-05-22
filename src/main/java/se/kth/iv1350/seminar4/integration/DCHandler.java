package se.kth.iv1350.seminar4.integration;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.seminar4.DTO.*;
import se.kth.iv1350.seminar4.discount.DiscountFinder;


/**
 * DCHandler (Discount Catalogue Handler) handles an external database of discounts. This is currently just a placeholder for an actual
 * database integration.
 */
public class DCHandler {
    ArrayList<DiscountDTO> discounts = new ArrayList<DiscountDTO>();
    
    /**
     * This function creates a new instance of the Discount catalog handler 
     */
    public DCHandler() {
        discounts.add(
            new DiscountDTO(.2, 200)
        );
        discounts.add(
            new DiscountDTO("identifier1", 10)
        );
        discounts.add(
            new DiscountDTO("identifier3", .1)
        );
    }
    
    /**
     * Finds discounts based on a sale and a finding method
     * @param saleDTO the sale where discounts later will be added
     * @param finder the method for finding discounts
     * @return List<DiscountDTO> a list of valid available discounts
     */
    public List<DiscountDTO> findDiscounts(SaleDTO saleDTO, DiscountFinder finder) {
        return finder.findDiscount(saleDTO, discounts);
    }    
}