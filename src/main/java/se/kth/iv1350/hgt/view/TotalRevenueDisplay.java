package se.kth.iv1350.hgt.view;

public abstract class TotalRevenueDisplay {
    public void newSaleWasMade(double priceOfTheSale) {
        calculateTotalIncome(priceOfTheSale);

        showTotalIncome();
    }

    private void showTotalIncome() {
        try {
            doShowTotalIncome();
        } catch(Exception e) {
            handleErrors(e);
        }
    }

    private void calculateTotalIncome(double priceOfTheSale) {

    }

    protected abstract void doShowTotalIncome();

    protected abstract void handleErrors(Exception e);
}
