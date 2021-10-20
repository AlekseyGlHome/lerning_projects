import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 31;
        int month = 12;
        int year = 1990;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {
        StringBuilder stringBuilder = new StringBuilder();
        Calendar calendar = GregorianCalendar.getInstance();
        Date currentDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - E", Locale.ENGLISH);
        int count = 0;
        calendar.set(year, month - 1, day);
        while (calendar.getTime().before(currentDate) || calendar.getTime().equals(currentDate)) {
            String dateFormatString = dateFormat.format(calendar.getTime());
            stringBuilder.append(count).append(" - ")
                         .append(dateFormatString)
                         .append(System.lineSeparator());
            calendar.add(Calendar.YEAR, 1);
            count++;
        }
        return stringBuilder.toString();
        //0 - 31.12.1990 - Mon
        //1 - 31.12.1991 - Tue
    }

}
