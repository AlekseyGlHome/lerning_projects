public abstract class Client {

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

    public abstract void printInfo();

}
