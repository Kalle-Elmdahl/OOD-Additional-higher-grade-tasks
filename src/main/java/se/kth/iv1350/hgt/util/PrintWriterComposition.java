package se.kth.iv1350.hgt.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

/**
 * PrintWriterComposition
 */
public class PrintWriterComposition {
    private PrintWriter writer;
    
    /**
     * Creates a new instance of the PrintWriterInhertance class
     * @throws IOException if the named file exists but is a directory rather than a regular file, 
     * does not exist but cannot be created, or cannot be opened for any other reason
     */
    public PrintWriterComposition() throws IOException {
        writer = new PrintWriter(new FileWriter("total-revenue-compotion.txt"), true);
    }

    /**
     * Prints a message to a file and current date and time the function also adds a new line at the end
     * @param message the message to be printed in the file
     */
    public void println(String message) {
        LocalTime timeOfWrite = LocalTime.now();
        writer.println("Print from " + timeOfWrite.getHour() + ":" + timeOfWrite.getMinute() + " \n" + message);
    }
}