package se.kth.iv1350.hgt.Integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.iv1350.hgt.DTO.*;
import se.kth.iv1350.hgt.integration.Printer;
import se.kth.iv1350.hgt.model.Item;
import se.kth.iv1350.hgt.model.Receipt;

import static org.junit.Assert.assertTrue;

public class PrinterTest {
    private Printer instance;
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @Before
    public void setUp() {
        printOutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
        
        instance = new Printer();
    }

    @After
    public void tearDown() {
        printOutBuffer = null;
        System.setOut(originalSysOut);

        instance = null;
    }

    @Test
    public void testPrinterLoggingCorrectly() {
        Receipt receipt = new Receipt(new PaymentDTO(100, "SEK"), new SaleDTO(LocalTime.now(), new ArrayList<Item>(), 10, 5));
        instance.printReceipt(receipt);
        String printOut = this.printOutBuffer.toString();
        String expectedOutput = "Printing receipt";
        assertTrue("Printer did not log correctly", printOut.contains(expectedOutput));
    }
}