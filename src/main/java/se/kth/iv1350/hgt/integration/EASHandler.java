package se.kth.iv1350.hgt.integration;

import se.kth.iv1350.hgt.DTO.*;

/**
 * EASHandler, external accounting system handler. This class fetches and writes data to the accounting database
 */
public class EASHandler {
    
    /** 
     * This registers a payment and a sale in the accounting system
     * @param payment The payment made by the customer
     * @param sale The sale containing items and date 
     */
    public void registerPayment(PaymentDTO payment, SaleDTO sale) {
        System.out.println("[LOG]: Regestering payment");
    }
}