package se.kth.iv1350.seminar4.discount;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.seminar4.DTO.DiscountDTO;
import se.kth.iv1350.seminar4.DTO.SaleDTO;
import se.kth.iv1350.seminar4.model.Item;
/**
 * ItemDiscount This class implements the Discountfinder class and finds discounts that can be added
 * to specific items.
 */
public class ItemDiscount implements DiscountFinder {

    @Override
    public List<DiscountDTO> findDiscount(SaleDTO saleDTO, List<DiscountDTO> availableDiscounts) {
        List<DiscountDTO> foundDiscounts = new ArrayList<DiscountDTO>();
        for(DiscountDTO discount : availableDiscounts) {
            for(Item item : saleDTO.getItems()) {
                if(!discount.getType().equals("item")) continue;
                if(item.getIdentifier().equals(discount.getIdOfDiscountedItem()))
                    foundDiscounts.add(discount);
            }
        }
        return foundDiscounts;
    }
    
}
