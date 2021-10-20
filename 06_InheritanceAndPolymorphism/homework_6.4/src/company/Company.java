package company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
    private double income;

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        employees.sort(Collections.reverseOrder());
        if (count > employees.size() || count < 0)
            count = 0;
        return new ArrayList<>(employees.subList(0, count));
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        Collections.sort(employees);
        if (count > employees.size() || count < 0)
            count = 0;

        return new ArrayList<>(employees.subList(0, count));
    }

    public void hire(Employee employee) {
        employee.setSalary(this);
        employees.add(employee);
    }

    public void hireAll(List<Employee> employee) {
        for (Employee emp : employee) {
            emp.setSalary(this);
            employees.add(emp);
        }
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public double getIncome() {
        return income;
    }

    protected void setIncome(double income) {
        this.income = income;
    }

}
