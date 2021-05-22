package se.kth.iv1350.hgt.Integration;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.iv1350.hgt.DTO.*;
import se.kth.iv1350.hgt.integration.EISHandler;
import se.kth.iv1350.hgt.integration.ItemNotFoundException;
import se.kth.iv1350.hgt.integration.ServerDownException;
import se.kth.iv1350.hgt.model.Item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;

public class EISHandlerTest {
    private EISHandler instance;
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @Before
    public void setUp() {
        printOutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);

        instance = new EISHandler();

    }

    @After
    public void tearDown() {
        printOutBuffer = null;
        System.setOut(originalSysOut);

        instance = null;
    }

    @Test
    public void testFindItem() {
        ItemDTO itemWithIdentifierTwo = new ItemDTO(
            "Ingredienser\n\nINGREDIENSER: Ekologiska skalade och k rossade tomater 65% (Portugal), ekologisk tomatjuice (tomat: Portugal), surhetsreglerandemedel (E 330 citronsyra). *KRAV-ekologisk ingrediens. KRAV-märkningen verifieras av Kiwa.\nFörvaring\n\nFörvaras vid högst 40°C\nÖvrigt\n\nEAN kod: 7340011491996", 
            12, 
            13.50, 
            "Tomater Krossade EKO", 
            "identifier2"
        );
        
        try {
            ItemDTO foundItem = instance.findItem("identifier2");
            assertEquals("Find item did not find correct item", foundItem.getName(), itemWithIdentifierTwo.getName());
            assertEquals("Find item did not find correct item", foundItem.getVAT(), itemWithIdentifierTwo.getVAT(), .01);
            assertEquals("Find item did not find correct item", foundItem.getPrice(), itemWithIdentifierTwo.getPrice(), .01);
            assertEquals("Find item did not find correct item", foundItem.getDescription(), itemWithIdentifierTwo.getDescription());
            assertEquals("Find item did not find correct item", foundItem.getIdentifier(), itemWithIdentifierTwo.getIdentifier());
        } catch (Exception exc) {
            fail("An exception was thrown on a valid identifier: " + exc.getMessage());
        }
    }

    @Test
    public void testFindItemWithInvalidIdentifier() {
        
        try {
            instance.findItem("This is an invalid identifier");
            fail("Exception was not thrown when it should have");
        } catch (ItemNotFoundException exc) {
            assertTrue("The exception message was wrong", exc.getMessage().contains("not found"));
        } catch (Exception exc) {
            fail("Wrong exception was thrown: " + exc.getMessage());
        }
    }

    @Test
    public void testServerDownException() {
        try {
            instance.findItem("ServerDownTest");
        } catch (ServerDownException exc) {
            assertTrue("The exception message was wrong", exc.getMessage().contains("server is down"));
        } catch (Exception exc) {
            fail("Wrong exception was thrown: " + exc.getMessage());
        }
    }

    @Test
    public void testUpdateInventoryLoggingCorrectly() {
        instance.updateInventory(new SaleDTO(LocalTime.now(), new ArrayList<Item>(), 10, 5));
        String printOut = this.printOutBuffer.toString();
        String expectedOutput = "updating inventory";
        assertTrue("EISHandler did not log correctly", printOut.contains(expectedOutput));
    }
}