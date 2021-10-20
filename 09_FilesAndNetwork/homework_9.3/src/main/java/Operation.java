public class Operation {
    private String name;
    private double income;
    private double expense;

    public Operation(String name, double income, double expense) {
        this.name = name;
        this.income = income;
        this.expense = expense;
    }

    public String getName() {
        return name;
    }

    public double getIncome() {
        return income;
    }

    public double getExpense() {
        return expense;
    }

}
