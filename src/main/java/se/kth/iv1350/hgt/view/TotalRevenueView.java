package se.kth.iv1350.hgt.view;

/**
 * TotalRevenueView This class writes to the console whenever notified.
 */
class TotalRevenueView extends TotalRevenueDisplay {
    @Override
    protected void doShowTotalIncome(double totalRevenue) throws Exception {
        System.out.println("--- A message from TotalRevenueobserver ---");
        System.out.println("The total revenue since the program started is: " + totalRevenue);
        System.out.println("-------------------------------------------");
        
    }

    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Could not display total revenue :(");
    }
}