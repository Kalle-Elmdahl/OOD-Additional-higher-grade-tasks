package se.kth.iv1350.seminar4.view;

import se.kth.iv1350.seminar4.model.SaleObserver;

/**
 * TotalRevenueView This class writes to the console whenever notified.
 */
class TotalRevenueView implements SaleObserver {
    private double totalRevenue;
    
    /**
     * Creates a new instance of the totalRevenueView class
     */
    TotalRevenueView() {
        totalRevenue = 0;
    }

    @Override
    public void newSale(double priceOfPurchase) {
        totalRevenue += priceOfPurchase;
        System.out.println("--- A message from TotalRevenueobserver ---");
        System.out.println("The total revenue since the program started is: " + totalRevenue);
        System.out.println("-------------------------------------------");
    }
}