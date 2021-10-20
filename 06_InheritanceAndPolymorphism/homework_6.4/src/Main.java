import company.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        for (int i = 0; i < 180; i++) {
            company.hire(new Operator());
        }
        for (int i = 0; i < 80; i++) {
            company.hire(new Manager());
        }
        List<Employee> listEmploe = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listEmploe.add(new TopManager());
        }
        company.hireAll(listEmploe);

        System.out.println("15 Самых высоких зарплат до увольнения");
        for (Employee emp : company.getTopSalaryStaff(15)) {
            System.out.printf("%,d руб.%n",emp.getMonthSalary());
        }
        System.out.println("15 Самых низких зарплат до увольнения");
        for (Employee emp : company.getLowestSalaryStaff(15)) {
            System.out.printf("%,d руб.%n",emp.getMonthSalary());
        }
        fire(company);
        System.out.println("15 Самых высоких зарплат после увольнения");
        for (Employee emp : company.getTopSalaryStaff(15)) {
            System.out.printf("%,d руб.%n",emp.getMonthSalary());
        }
        System.out.println("15 Самых низких зарплат после увольнения");
        for (Employee emp : company.getLowestSalaryStaff(15)) {
            System.out.printf("%,d руб.%n",emp.getMonthSalary());
        }
    }

    public static void fire(Company company) {
        List<Employee> emp = company.getEmployees();
        Collections.shuffle(emp);
        for (int i = 0; i < emp.size() / 2; i++) {
            company.fire(emp.get(i));
        }
    }
}
