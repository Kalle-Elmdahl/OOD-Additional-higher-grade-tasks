package se.kth.iv1350.seminar4.Startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import se.kth.iv1350.seminar4.startup.Main;

public class MainTest {
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @Before
    public void setUp() {

        printOutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @After
    public void tearDown() {
        printOutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testUIHasStarted() {
        String[] args = null;
        Main.main(args);
        String printOut = this.printOutBuffer.toString();
        String expectedOutput = "started";
        assertTrue("UI did not start correctly", printOut.contains(expectedOutput));
    }
}