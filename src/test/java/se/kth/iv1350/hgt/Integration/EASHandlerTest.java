package se.kth.iv1350.hgt.Integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.iv1350.hgt.DTO.*;
import se.kth.iv1350.hgt.integration.EASHandler;
import se.kth.iv1350.hgt.model.Item;

import static org.junit.Assert.assertTrue;

public class EASHandlerTest {
    private EASHandler instance;
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @Before
    public void setUp() {
        printOutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
        
        instance = new EASHandler();
    }

    @After
    public void tearDown() {
        printOutBuffer = null;
        System.setOut(originalSysOut);

        instance = null;
    }

    @Test
    public void testEASHandlerLoggingCorrectly() {
        instance.registerPayment(new PaymentDTO(100, "SEK"), new SaleDTO(LocalTime.now(), new ArrayList<Item>(), 10, 5));
        String printOut = this.printOutBuffer.toString();
        String expectedOutput = "Regestering payment";
        assertTrue("EASHandler did not log correctly", printOut.contains(expectedOutput));
    }
}