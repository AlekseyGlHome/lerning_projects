import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static final String PATH_CSV = "/home/aleksey/Рабочий стол/movementList.csv";

    public static void main(String[] args) {
        Movements movements = new Movements(PATH_CSV);
        System.out.printf("Сумма расходов: %,.2f руб.\n", movements.getExpenseSum());
        System.out.printf("Сумма доходов: %,.2f руб.\n", movements.getIncomeSum());

        System.out.println();
        System.out.println("Суммы расходов по организациям:");
        Map<String, List<Operation>> oper = movements.getAmountsByGroup();

        for (Map.Entry<String, List<Operation>> item : oper.entrySet()) {
            double amountGroups = 0.0;
            String nameGroups = item.getKey();
            int count = 0;
            for (Operation op : item.getValue()) {
                amountGroups += op.getExpense();
                count++;
            }
            System.out.printf("%-20s покупок[%-3s] %,.2f руб.\n", nameGroups, count, amountGroups);
        }
    }
}
