package se.kth.iv1350.seminar4.DTO;

/**
 * This is a data transfer object class used to send the customer's payment.
 */
public class PaymentDTO {
    double amount;
    String currency;

    /**
     * This function creates a new instance of a payment data transfer object
     * @param amount the amount the customer has paid
     * @param currency the currenct the customer paid in
     */
    public PaymentDTO(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    
    /** 
     * This function gets gets the amount paid by the customer
     * @return double the amount paid
     */
    public double getAmount() {
        return this.amount;
    }

    
    /** 
     * This function gets gets the currency used by the customer
     * @return String the currency
     */
    public String getCurrency() {
        return this.currency;
    }
}
