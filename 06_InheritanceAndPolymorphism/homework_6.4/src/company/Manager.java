package company;

import java.util.Random;

public class Manager implements Employee {

    private int salary;

    @Override
    public int getMonthSalary() {
        return salary;
    }

    @Override
    public int compareTo(Employee employee) {
        return salary - employee.getMonthSalary();
    }

    @Override
    public void setSalary(Company company) {
        Random random = new Random();
        salary = 50_000 + random.nextInt(75_000 - 50_000);

        int amountIncome = 115_000 + random.nextInt(140_000 - 115_000);
        int percentSalary = (int) (amountIncome * 0.05);
        salary += percentSalary;
        company.setIncome(company.getIncome() + amountIncome);
    }
}
