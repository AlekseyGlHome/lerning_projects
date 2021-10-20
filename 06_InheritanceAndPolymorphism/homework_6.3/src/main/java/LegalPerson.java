public class LegalPerson extends Client {
    private final double COMMISSION_PERCENTAGE = 1;

    @Override
    public void take(double amountToTake) {
        if (amountToTake > 0) {
            double percentSumma = (amountToTake * COMMISSION_PERCENTAGE) / 100;
            super.take(amountToTake + percentSumma);
        }
    }

    @Override
    public void printInfo() {
        System.out.println("У юридических лиц — снятие с комиссией 1%.\n" +
                "Остаток на счете:"+getAmount());
    }

}
