package se.kth.iv1350.seminar4.Controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.DTO.*;
import se.kth.iv1350.seminar4.integration.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ControllerTest {
    private Controller instance;
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @Before
    public void setUp() {
        printOutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
        
        EASHandler eas = new EASHandler();
        EISHandler eis = new EISHandler();
        Printer printer = new Printer();
        DCHandler dc = new DCHandler();

        instance = new Controller(eis, eas, printer, dc);

    }

    @After
    public void tearDown() {
        printOutBuffer = null;
        System.setOut(originalSysOut);

        instance = null;
    }

    @Test
    public void testControllerHasStarted() {
        String printOut = this.printOutBuffer.toString();
        String expectedOutput = "success";
        assertTrue("Controller did not start correctly", printOut.contains(expectedOutput));
    }

    @Test
    public void addItem() {
        instance.startSale();
        try {
            SaleInfoDTO test = instance.enterItem("identifier1");
            double runningTotal = test.getRunningTotal();
            assertEquals("Add duplicate is not working", 59.4, runningTotal, .1);
        } catch (Exception exc) {
            fail("An exception was thrown on a valid identifier: " + exc.getMessage());
        }
    }

    @Test
    public void addMultipleOfSame() {
        instance.startSale();
        String identifier = "identifier1";
        try {
            instance.enterItem(identifier);
            SaleInfoDTO secondOfSame = instance.enterItem(identifier);
            int quantity = secondOfSame.getCurrentItemQuantity();
            assertEquals("Add Multiple of same item is not working", 2, quantity);
        } catch (Exception exc) {
            fail("An exception was thrown on a valid identifier: " + exc.getMessage());
        }
    }

    @Test
    public void checkIfChangeIsCalculatedCorrectly() {
        instance.startSale();
        try {
            instance.enterItem("identifier1");
            double change = instance.pay(90, "SEK");
            assertEquals("Calculation of change is not working", 90 - 59.4, change, .01);
        } catch (Exception exc) {
            fail("An exception was thrown on a valid identifier: " + exc.getMessage());
        }
    }
}