public class BankAccount {

    private double accountBalance;

    public double getAmount() {
        return accountBalance;
    }

    public void put(double amountToPut) {
        if (amountToPut > 0) {
            accountBalance += amountToPut;
        }
    }

    public void take(double amountToTake) {
        if (Double.compare(accountBalance, amountToTake) >= 0 && amountToTake > 0) {
            accountBalance -= amountToTake;
        }

    }

    public boolean send(BankAccount receiver, double amount) {
        double ost = getAmount();
        take(amount);
        if (Double.compare(ost, getAmount()) > 0) {
            receiver.put(amount);
            return true;
        }
        return false;
    }
}
