package company;

import java.util.Random;

public class TopManager implements Employee {
    private int salary;

    @Override
    public int getMonthSalary() {
        return salary;
    }

    @Override
    public void setSalary(Company company) {
        Random random = new Random();
        salary = 80_000 + random.nextInt(95_000 - 80_000);
        if (company.getIncome() > 10_000_000) {
            int percentSalary = (int) (salary * 1.5);
            salary += percentSalary;
        }
    }

    @Override
    public int compareTo(Employee employee) {
        return salary - employee.getMonthSalary();
    }
}
