import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Файл test/resources/movementListGroup.csv")
public class testMovement {

    private static final String CSV_FILENAME = "movementListGroup.csv";

    @Test
    @DisplayName("Сумма расхода по группам")
    void testAmountByGroups() {
        Map<String, Double> expected = new HashMap<>();
        Map<String, Double> actual = new HashMap<>();

        expected.put("KUSCHAVEL",300.00);
        expected.put("Alfa Iss",33900.00);
        expected.put("YANDEX TAXI",511.00);
        Map<String,List<Operation>> oper = new Movements(getCsvFilenamePath()).getAmountsByGroup();
        for (Map.Entry<String, List<Operation>> item : oper.entrySet()) {
            double amountGroups = 0.0;
            String nameGroups = item.getKey();
            for (Operation op : item.getValue()) {
                amountGroups += op.getExpense();
            }
            actual.put(nameGroups,amountGroups);
        }
        assertEquals(expected, actual);
    }

    private String getCsvFilenamePath() {
        return new File(this.getClass().getResource(CSV_FILENAME).getPath()).getAbsolutePath();
    }

}
