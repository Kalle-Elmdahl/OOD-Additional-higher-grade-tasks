package se.kth.iv1350.hgt.Model;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import se.kth.iv1350.hgt.model.Register;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RegisterTest {
    private Register instance;
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @Before
    public void setUp() {
        printOutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
        
        instance = Register.getInstance();
    }

    @After
    public void tearDown() {
        printOutBuffer = null;
        System.setOut(originalSysOut);

        instance = null;
    }

    @Test
    public void testUpdateRegisterAmount() {
        double startAmount = instance.getAmount();
        instance.updateAmount(50);
        assertEquals("UpdateAmount not working for positive numbers", startAmount + 50, instance.getAmount(), .01);
        instance.updateAmount(-30);
        assertEquals("UpdateAmount not working for negative numbers", startAmount + 50 - 30, instance.getAmount(), .01);
    }
    
    @Test
    public void testRegisterLoggingCorrectly() {
        instance.updateAmount(100);
        String printOut = this.printOutBuffer.toString();
        String expectedOutput = "amount in register has been updated";
        assertTrue("Register did not log correctly", printOut.contains(expectedOutput));
    }
}