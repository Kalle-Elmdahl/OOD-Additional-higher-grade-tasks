package se.kth.iv1350.seminar4.Model;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.iv1350.seminar4.model.Sale;
import se.kth.iv1350.seminar4.model.Item;
import se.kth.iv1350.seminar4.model.Receipt;
import se.kth.iv1350.seminar4.DTO.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaleTest {
    private Sale instance;

    @Before
    public void setUp() {
        instance = new Sale();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testAddItem() {
        String testIdentifier = "Identifier";
        instance.addItem(new ItemDTO("Test item desciption", 1, 10, "Test", testIdentifier));
        ArrayList<Item> items = instance.getItems();
        assertEquals("Item was not added successfully", testIdentifier, items.get(0).getIdentifier());
    }

    @Test
    public void testAddDuplicateItem() {
        String testIdentifier = "Identifier";
        instance.addItem(new ItemDTO("Test item desciption", 1, 10, "Test", testIdentifier));
        instance.addDuplicate(testIdentifier);
        ArrayList<Item> items = instance.getItems();
        assertEquals("Add duplicate is not working", 2, items.get(0).getQuantity());
    }

    @Test
    public void testIsDuplicateTrue() {
        String testIdentifier = "Identifier";
        instance.addItem(new ItemDTO("Test item desciption", 1, 10, "Test", testIdentifier));
        boolean isDuplicate = instance.isDuplicate(testIdentifier);
        assertTrue("is duplicate is not working", isDuplicate);
    }

    @Test
    public void testIsDuplicateFalse() {
        String testIdentifier = "Identifier";
        instance.addItem(new ItemDTO("Test item desciption", 1, 10, "Test", testIdentifier));
        boolean isDuplicate = instance.isDuplicate("Not the same");
        assertTrue("is duplicate is not working", !isDuplicate);
    }

    @Test
    public void testCalculationOfTotalPrice() {
        instance.addItem(new ItemDTO("Test item desciption", 1, 10, "Test", "Identifier1"));
        instance.addItem(new ItemDTO("Test item desciption", 1, 25, "Test", "Identifier2"));
        instance.addDuplicate("Identifier2");
        double totalPrice = instance.getTotalPrice();
        assertEquals("total price was not calculated correcly", 10 + 25 * 2, totalPrice, .01);
    }

   @Test
   public void testConvertToDTO() {
        instance.addItem(new ItemDTO("Test item desciption", 1, 10, "Test", "Identifier1"));
        instance.addItem(new ItemDTO("Test item desciption", 1, 25, "Test", "Identifier2"));
        SaleDTO convertedInstance = instance.convertToDTO();

        assertEquals(
            "Sale was not converted to DTO successfully (Sizeof items)", 
            instance.getItems().size(), 
            convertedInstance.getItems().size()
        );

        for(int i = 0; i < instance.getItems().size(); i++)
            assertTrue(
                "Sale was not converted to DTO successfully (Items)",
                instance.getItems().get(i).getIdentifier().equals(convertedInstance.getItems().get(i).getIdentifier())
            );

        
        assertEquals("Sale was not converted to DTO successfully (totalPrice)", instance.getTotalPrice(), convertedInstance.getTotalPrice(), .01);
        assertEquals("Sale was not converted to DTO successfully (totalVAT)", instance.getTotalVAT(), convertedInstance.getTotalVAT(), .01);
   } 

   @Test
   public void testCompleteSale() {
        instance.addItem(new ItemDTO("Test item desciption", 1, 10, "Test", "Identifier1"));
        instance.addItem(new ItemDTO("Test item desciption", 1, 25, "Test", "Identifier2"));

        SaleDTO convertedInstance = instance.convertToDTO();
        PaymentDTO payment = new PaymentDTO(10, "SEK");
        Receipt receipt = instance.complete(payment, convertedInstance);

        assertEquals(
            "Receipt was not created successfully (Items) (Sizeof items)", 
            instance.getItems().size(), 
            convertedInstance.getItems().size()
        );

        for(int i = 0; i < instance.getItems().size(); i++)
            assertEquals(
                "Receipt was not created successfully (Items)",
                convertedInstance.getItems().get(i).getIdentifier(),
                receipt.getItems().get(i).getIdentifier()
            );

        assertEquals("Receipt was not created successfully (Time)", convertedInstance.getTime(), receipt.getTime());

        assertEquals("Receipt was not created successfully (Price)", convertedInstance.getTotalPrice(), receipt.getTotalPrice(), .01);
        assertEquals("Receipt was not created successfully (VAT)", convertedInstance.getTotalVAT(), receipt.getTotalVAT(), .01);
        
        assertEquals("Receipt was not created successfully (Amount)", payment.getAmount(), receipt.getAmountPaid(), .01);
        assertEquals("Receipt was not created successfully (currency,)", payment.getCurrency(), receipt.getCurrency());

   } 
}