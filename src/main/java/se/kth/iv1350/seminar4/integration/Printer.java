package se.kth.iv1350.seminar4.integration;

import se.kth.iv1350.seminar4.model.Receipt;

/**
 * Printer. This class sends data to an external printer
 */
public class Printer {

    
    /** 
     * This function prints a receipt. This is a dummy function because a printer could not be implemented
     * @param receipt
     */
    public void printReceipt(Receipt receipt) {
        System.out.println("[LOG]: Printing receipt");
    }
}