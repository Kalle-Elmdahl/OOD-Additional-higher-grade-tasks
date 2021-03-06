package se.kth.iv1350.hgt.integration;

import se.kth.iv1350.hgt.model.Receipt;

/**
 * Printer. This class sends data to an external printer
 */
public class Printer {

    
    /** 
     * This function prints a receipt. This is a dummy function because a printer could not be implemented
     * @param receipt the receipt to be printed
     */
    public void printReceipt(Receipt receipt) {
        System.out.println("[LOG]: Printing receipt");
    }
}