package company;

import java.util.Random;

public class Operator implements Employee {
    private int salary;

    @Override
    public int getMonthSalary() {
        return salary;
    }

    @Override
    public void setSalary(Company company) {
        Random random = new Random();
        salary = 20_000 + random.nextInt(35_000 - 20_000);

    }


    @Override
    public int compareTo(Employee employee) {

        return salary - employee.getMonthSalary();
    }
}
