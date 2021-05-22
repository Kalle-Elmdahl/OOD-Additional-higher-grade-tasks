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

    public PrintWriterComposition() throws IOException {
        writer = new PrintWriter(new FileWriter("total-revenue-compotion.txt"), true);
    }

    public void println(String message) {
        LocalTime timeOfWrite = LocalTime.now();
        writer.println("Print from " + timeOfWrite.getHour() + ":" + timeOfWrite.getMinute() + " \n" + message);
    }
}