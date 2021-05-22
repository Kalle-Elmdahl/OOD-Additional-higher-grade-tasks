package se.kth.iv1350.seminar4.integration;

import java.util.ArrayList;

import se.kth.iv1350.seminar4.DTO.*;

/**
 * EISHandler, external inventory system handler. This class fetches data and writes to the inventory database
 */
public class EISHandler {
    ArrayList<ItemDTO> items = new ArrayList<ItemDTO>();
    
    public EISHandler() {
        items.add(
            new ItemDTO(
                "Ingredienser\nKycklingbröstfilé (100%)\n\nFörvaring\nFörvaras vid högst 4°C\n\nÖvrigt\nEAN kod: 2307111100000", 
                12, 
                59.4, 
                "Kycklingfilé Tunnskivad", 
                "identifier1"
            )
        );
        items.add(
            new ItemDTO(
                "Ingredienser\n\nINGREDIENSER: Ekologiska skalade och k rossade tomater 65% (Portugal), ekologisk tomatjuice (tomat: Portugal), surhetsreglerandemedel (E 330 citronsyra). *KRAV-ekologisk ingrediens. KRAV-märkningen verifieras av Kiwa.\nFörvaring\n\nFörvaras vid högst 40°C\nÖvrigt\n\nEAN kod: 7340011491996", 
                12, 
                13.50, 
                "Tomater Krossade EKO", 
                "identifier2"
            )
        );
        items.add(
            new ItemDTO(
                "Övrigt\n\nEAN kod: 7300156590244", 
                12, 
                22.95, 
                "Potatis Fast", 
                "identifier3"
            )
        );
    }
    
    /** 
     * This function gets the correct item from the external inventory system based on an idenfier
     * @param identifier the item's identifier
     * @return ItemDTO the found item
     * @throws ItemNotFoundException When identifier parameter is invalid 
     * @throws ServerDownException When the server is down
     */
    public ItemDTO findItem(String identifier) throws ItemNotFoundException, ServerDownException {
        if(identifier.equals("ServerDownTest")) 
            throw new ServerDownException("The server is down");
        
        for (ItemDTO item : items)
            if(item.getIdentifier().equals(identifier)) 
                return item;
            
        throw new ItemNotFoundException("An item with the specified identifier was not found. The provided identifier: " + identifier);
    }

    
    /** 
     * The function updates the inventory based on the items sold in a sale
     * @param sale the completed sale in a data transfer object
     */
    public void updateInventory(SaleDTO sale) {
        System.out.println("[LOG]: updating inventory");
    }
}
