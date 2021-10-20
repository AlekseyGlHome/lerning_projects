import java.util.Scanner;

public class Main {

    public static final int TRUCK_MAX_CONTAINERS = 12;
    public static final int CONTAINER_MAX_BOXES = 27;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        try{
            int boxCount = Integer.parseInt(boxes);
            int containerCount = boxCount / CONTAINER_MAX_BOXES + ((boxCount % CONTAINER_MAX_BOXES > 0) ? 1:0);
            int truckCount = containerCount / TRUCK_MAX_CONTAINERS + ((containerCount % TRUCK_MAX_CONTAINERS > 0) ? 1:0);
            int container = 1;
            int box = 1;
            for (int i = 0; i < truckCount; i++){
                System.out.println("Грузовик: " + (i+1));
                for (int j = 0; j < TRUCK_MAX_CONTAINERS && container<=containerCount; j++){
                    System.out.println("\tКонтейнер: " + container);
                    container++;
                    for (int k = 0; k < CONTAINER_MAX_BOXES && box<=boxCount; k++){
                        System.out.println("\t\tЯщик: " + box);
                        box++;
                    }
                }
            }

            System.out.printf("Необходимо:"+System.lineSeparator()+"грузовиков - %s шт."+
                    System.lineSeparator()+"контейнеров - %s шт.",truckCount,containerCount);

        }catch (NumberFormatException ex){
            System.out.println("Неверный форма введенных данных");
        }


        // пример вывода при вводе 2
        // для отступа используйте табуляцию - \t

        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */
    }

}
