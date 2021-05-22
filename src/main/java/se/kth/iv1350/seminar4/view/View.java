package se.kth.iv1350.seminar4.view;

import java.util.ArrayList;

import se.kth.iv1350.seminar4.DTO.*;
import se.kth.iv1350.seminar4.controller.Controller;
import se.kth.iv1350.seminar4.integration.ItemNotFoundException;
import se.kth.iv1350.seminar4.integration.ServerDownException;

/**
 * This is a pleaceholder for the real view. It contains a hardcoded execution with calls to all 
 * system operations in the controller
 */
public class View {
    private Controller contr;

    /**
     * Creates a new instance, that uses the specifier controller for all calls to other layers
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) {
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
        contr.addSaleObserver(new TotalRevenueFileOutput());
    }

    /**
     * Performs a fake sale, by calling all system operations in the controller.
     */
    public void runFakeExecution() {
        System.out.println("\n\n\n===================\nStarting sale 1\n===================");
        contr.startSale();
        System.out.println("A new sale has been started.");
        try {
            contr.enterItem("identifier1");
            System.out.println("Added an item with identifier: identifier1");
        } catch (ItemNotFoundException e) {
            System.err.println("Could not find item");
        } catch (ServerDownException e) {
            System.err.println("The server is down right now, please try again later :)");
        }

        System.out.println("\n\n\n===================\nApplying discounts\n===================");
        System.out.println("The total price is now " + contr.applyDiscounts());

        System.out.println("\n\n\n===================\nCompleting  sale\n===================");
        double change = contr.pay(500, "SEK");
        System.out.println("A payment of 500 SEK was made and got change: " + change);



        System.out.println("\n\n\n===================\nStarting sale 2\n===================");
        contr.startSale();
        System.out.println("A new sale has been started.");

        ArrayList<String> customersItems = new ArrayList<String>();
        customersItems.add("identifier1");
        customersItems.add("identifier1");
        customersItems.add("identifier1");
        customersItems.add("identifier2");
        customersItems.add("identifier2");
        customersItems.add("invalid");
        customersItems.add("identifier3");
        customersItems.add("identifier3");
        customersItems.add("identifier3");
        for(String identifier : customersItems) {
            try {
                SaleInfoDTO info = contr.enterItem(identifier);
                System.out.println("\nAdded an item with identifier:" + identifier);
                System.out.println("Added item " + info.getCurrentItemName() + ", the running total is now " + info.getRunningTotal());
            } catch (ItemNotFoundException e) {
                System.err.println("Could not find item");
            } catch (ServerDownException e) {
                System.err.println("The server is down right now, please try again later :)");
            }
        }

        System.out.println("\n\n\n===================\nApplying discounts\n===================");
        System.out.println("The total price is now " + contr.applyDiscounts());

        System.out.println("\n\n\n===================\nCompleting  sale\n===================");
        change = contr.pay(500, "SEK");
        System.out.println("A payment of 500 SEK was made and got change: " + change);
    }
}