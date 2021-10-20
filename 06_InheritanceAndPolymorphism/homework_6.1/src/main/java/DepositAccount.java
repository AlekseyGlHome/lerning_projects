import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DepositAccount extends BankAccount {
    private LocalDate lastIncome;


    @Override
    public void put(double amountToPut) {
        double ost = getAmount();
        super.put(amountToPut);
        if (Double.compare(getAmount(), ost) > 0) {
            lastIncome = LocalDate.now();
        }
    }

    @Override
    public void take(double amountToTake) {
        if (getAmount() > 0) {
            long data_dey = ChronoUnit.MONTHS.between(lastIncome, LocalDate.now());
            if (data_dey >= 1) {
                super.take(amountToTake);
            }
        }
    }
}
