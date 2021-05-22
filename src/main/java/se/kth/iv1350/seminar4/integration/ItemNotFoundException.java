package se.kth.iv1350.seminar4.integration;

/**
 * ItemNotFoundException This exception is called when the external inventory system can not find the sent identifier
 */
public class ItemNotFoundException extends Exception {
    /**
     * This function creates a new instance of the ItemNotFoundException
     * @param message The message to be shown for debugging purposes.
     */
    public ItemNotFoundException(String message) {
        super(message);
    }
}