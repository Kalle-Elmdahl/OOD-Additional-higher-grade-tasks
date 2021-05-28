package se.kth.iv1350.hgt.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * PrintWriterInhertance
 */
public class PrintWriterInhertance extends PrintWriter {
    /**
     * Creates a new instance of the PrintWriterInhertance class
     * @throws IOException if the named file exists but is a directory rather than a regular file, 
     * does not exist but cannot be created, or cannot be opened for any other reason
     */
    public PrintWriterInhertance() throws IOException {
        super(new FileWriter("total-revenue-inheritance.txt"), true);
    }

    /**
     * Prints a message to a file and current date and time the function also adds a new line at the end
     * @param message the message to be printed in the file
     */
    @Override
    public void println(String message) {
        LocalTime timeOfWrite = LocalTime.now();
        LocalDate dateOfWrite = LocalDate.now();
        super.println("Print from " + dateOfWrite + " at " + timeOfWrite.getHour() + ":" + timeOfWrite.getMinute() + " \n" + message);
    }
    
}