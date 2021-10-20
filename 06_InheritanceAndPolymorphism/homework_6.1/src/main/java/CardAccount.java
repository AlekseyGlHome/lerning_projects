public class CardAccount extends BankAccount {

    private final double COMMISSION_PERCENTAGE = 1;

    @Override
    public void take(double amountToTake) {
        if (amountToTake > 0) {
            double percentSumma = (amountToTake * COMMISSION_PERCENTAGE) / 100;
            super.take(amountToTake + percentSumma);
        }
    }
}
