package company;

public interface Employee extends Comparable<Employee> {

    int getMonthSalary();

    void setSalary(Company company);
}
