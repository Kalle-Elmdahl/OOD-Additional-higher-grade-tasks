package se.kth.iv1350.seminar4.discount;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.seminar4.DTO.DiscountDTO;
import se.kth.iv1350.seminar4.DTO.SaleDTO;

/**
 * SaleDiscount This class implements the Discountfinder class and finds discounts that can be added
 * to the whole sale.
 */
public class SaleDiscount implements DiscountFinder {

    @Override
    public List<DiscountDTO> findDiscount(SaleDTO saleDTO, List<DiscountDTO> availableDiscounts) {
        List<DiscountDTO> foundDiscounts = new ArrayList<DiscountDTO>();
        for(DiscountDTO discount : availableDiscounts) {
            if(!discount.getType().equals("sale")) continue;
            if(saleDTO.getTotalPrice() >= discount.getMinRequiredPrice())
                foundDiscounts.add(discount);
        }
        return foundDiscounts;
    }

    
}