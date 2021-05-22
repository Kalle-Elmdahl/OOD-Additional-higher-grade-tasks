package se.kth.iv1350.hgt.View;



import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.iv1350.hgt.controller.Controller;
import se.kth.iv1350.hgt.integration.*;
import se.kth.iv1350.hgt.view.View;

import static org.junit.Assert.assertTrue;

public class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @Before
    public void setUp() {
        EASHandler eas = new EASHandler();
        EISHandler eis = new EISHandler();
        Printer printer = new Printer();
        DCHandler dc = new DCHandler();

        Controller contr = new Controller(eis, eas, printer, dc);
        instanceToTest = new View(contr);

        printOutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @After
    public void tearDown() {
        instanceToTest = null;

        printOutBuffer = null;
        System.setOut(originalSysOut);
    }
    
    @Test
    public void viewTest() {
        instanceToTest.runFakeExecution();
        String printOut = this.printOutBuffer.toString();
        String expectedOutput = "started";
        assertTrue("UI did not start correctly", printOut.contains(expectedOutput));
    }
}