package se.kth.iv1350.seminar4.model;

/**
 * The register is a singleton class and therefore is accessed by the getInstance function
 */
public class Register {
    double amount;
    private static final Register REGISTER = new Register();

    private Register() {
        this.amount = 1000;
    }
    
    /**
     * This function gets the register.
     * @return Register the register
     */
    public static Register getInstance() {
      return REGISTER;
    }

    /** 
     * Returns the amount in register.
     * @return double: the amount of money in the register.
     */
    public double getAmount() {
        return amount;
    }

    
    /** 
     * This function can update the amount in the register.
     * @param amount the relative change of the amount. Can be negative.
     */
    public void updateAmount(double amount) {
        this.amount += amount;
        System.out.println("[LOG]: The amount in register has been updated, there is now " + amount + " in the register");
    }
}
