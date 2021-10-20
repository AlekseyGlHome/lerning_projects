import com.skillbox.airport.Airport;

public class Main
{
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        //Вариант 1 распечатки всех самолоетов
        //airport.getAllAircrafts().forEach(System.out::println);

        // Вариант 2 распечатки всех самолоетов
        //for (var el:airport.getAllAircrafts()){
        //    System.out.println(el);
        //}

        int countAircraft = airport.getAllAircrafts().size();
        System.out.printf("Количество самолетов: %s",countAircraft);
    }
}
