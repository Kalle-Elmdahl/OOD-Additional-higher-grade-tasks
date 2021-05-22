package se.kth.iv1350.seminar4.integration;

/**
 * ServerDownException This exception is thrown when the external database is down
 */
public class ServerDownException extends Exception {
    /**
     * This function creates a new instance of the ServerDownException
     * @param message the message to be used for debugging purposes
     */
    public ServerDownException(String message) {
        super(message);
    }
}
