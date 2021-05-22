package se.kth.iv1350.seminar4.startup;

import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.view.View;
import se.kth.iv1350.seminar4.integration.*;

/**
 * Starts the entire application. Contains the main method used to start the application.
 */
public class Main {
    /**
     * The main method used to start the application.
     * @param args The applicaiton does not take any command line parameters.
     */
    public static void main(String[] args) {
        EASHandler eas = new EASHandler();
        EISHandler eis = new EISHandler();
        Printer printer = new Printer();
        DCHandler dc = new DCHandler();

        Controller contr = new Controller(eis, eas, printer, dc);
        View view = new View(contr);

        view.runFakeExecution();
    }
}