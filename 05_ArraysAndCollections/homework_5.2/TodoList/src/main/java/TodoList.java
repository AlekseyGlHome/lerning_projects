import java.util.ArrayList;

public class TodoList {

    private final ArrayList<String> toDoListDB = new ArrayList<>();

    public void add(String todo) {
        if (todo != "") {
            toDoListDB.add(todo);
        }

    }

    public void add(int index, String todo) {
        if (todo != "") {
            if (index < toDoListDB.size()) {
                toDoListDB.add(index, todo);
            } else {
                toDoListDB.add(todo);
            }

        }
    }

    public void edit(String todo, int index) {
        if (index < toDoListDB.size() && todo != "") {
            toDoListDB.set(index, todo);
        }
    }

    public void delete(int index) {
        if (index < toDoListDB.size()) {
            toDoListDB.remove(index);
        }
    }

    public ArrayList<String> getTodos() {
        return toDoListDB;
    }

}