import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Movements {

    private final ArrayList<Operation> operations = new ArrayList<>();

    public ArrayList<Operation> getOperations() {
        return operations;
    }


    public Movements(String pathMovementsCsv) {
        try {
            CSVReader reader = new CSVReader(new FileReader(pathMovementsCsv), ',', '"', '/', 1);

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String operationName;
                double income = getAmount(nextLine[6]);
                double expense = getAmount(nextLine[7]);
                if (expense > 0) {
                    operationName = getOperationName(nextLine[5]);
                } else {
                    operationName = nextLine[5];
                }
                operations.add(new Operation(operationName, income, expense));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getOperationName(String operationName) {
        String[] name = operationName.split(" {3} +");
        return name[1].substring(name[1].lastIndexOf("\\") + 1).trim();
    }

    private static double getAmount(String amount) {
        try {
            return Double.parseDouble(amount.replaceAll(",", "."));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public double getExpenseSum() {
        return operations.stream()
                .map(Operation::getExpense)
                .filter(e -> e > 0)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    public double getIncomeSum() {
        return operations.stream()
                .map(Operation::getIncome)
                .filter(e -> e > 0)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    public Map<String, List<Operation>> getAmountsByGroup() {
        return operations.stream()
                .filter((o -> o.getExpense() > 0))
                .collect(Collectors.groupingBy(Operation::getName));
    }

}
