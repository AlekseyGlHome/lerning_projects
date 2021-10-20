import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String ERROR_MESSAGE = "Неверный формат команды";
    private static TodoList toDoList = new TodoList();

    public static void main(String[] args) {
        System.out.println("Введите команду QUIT - выход, ADD - добавить, EDIT - Изменить, DELETE - удалить");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.trim().equals("QUIT")) {
                break;
            }
            String[] command = input.trim().split("\\s+", 2);
            if (command.length == 0) {
                continue;
            }
            parsing(command);
        }
    }

    private static void parsing(String[] command) {
        switch (command[0]) {
            case "LIST":
                printToDoList();
                break;
            case "DELETE":
                if (command.length > 1) {
                    deleteToDo(command[1]);
                } else {
                    System.out.println(ERROR_MESSAGE);
                }
                break;
            case "EDIT":
                if (command.length > 1) {
                    editToDo(command[1]);
                } else {
                    System.out.println(ERROR_MESSAGE);
                }
                break;
            case "ADD":
                if (command.length > 1) {
                    addToDo(command[1]);
                } else {
                    System.out.println(ERROR_MESSAGE);
                }
                break;
            default:
                System.out.println("Неизвестная команда");
                break;
        }
    }

    private static void addToDo(String s) {
        String[] command = s.trim().split("\\s+", 2);
        if (isDigits(command[0])) {
            int index = Integer.parseInt(command[0]);
            toDoList.add(index, command[1]);
        } else {
            toDoList.add(s);
        }
    }

    private static boolean isDigits(String s) {
        boolean isDigit = true;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                isDigit = false;
            }
        }
        return isDigit;
    }

    private static void editToDo(String s) {
        String[] command = s.trim().split("\\s+", 2);
        if (isDigits(command[0])) {
            int index = Integer.parseInt(command[0]);
            toDoList.edit(command[1], index);
        } else {
            System.out.println("Индекс не является числом");
        }
    }

    private static void deleteToDo(String s) {
        try {
            int index = Integer.parseInt(s);
            toDoList.delete(index);
        } catch (NumberFormatException ex) {
            System.out.println("Параметр не является числом");
        }
    }

    private static void printToDoList() {
        ArrayList<String> list = toDoList.getTodos();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " - " + list.get(i));
        }
    }
}
