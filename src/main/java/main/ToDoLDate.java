package main;
import main.model.ToDoList;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ToDoLDate {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    private static int id = atomicInteger.incrementAndGet();
    private final static Map<Integer, ToDoList> toDoLS = createNewMap();

    private static Map<Integer, ToDoList> createNewMap() {
        return Collections.synchronizedMap(new HashMap<>());
    }

    public static List<ToDoList> getAllToDoL() {
        synchronized (toDoLS) {
            ArrayList<ToDoList> toDoList = new ArrayList<>();
            toDoList.addAll(toDoLS.values());
            return toDoList;
        }
    }

    public static int addToDoL(ToDoList toDoL) {
        synchronized (ToDoLDate.class) {
            toDoL.setId(id);
            toDoLS.put(id, toDoL);
            return id;
        }
    }

    public static ToDoList editToDoList(ToDoList toDoL) { 
        synchronized (ToDoLDate.class) {
            int id = toDoL.getId();
            toDoLS.replace(id, toDoL);
            return toDoL;
        }
    }

    public static ToDoList getRecord(int idToDoDate) {
        synchronized (toDoLS) {
            if (toDoLS.containsKey(idToDoDate)) {
                return toDoLS.get(idToDoDate);
            }
            return null;
        }
    }

    public static void deleteToDo(int toDoId) {
        synchronized (toDoLS) {
            toDoLS.remove(toDoId);
        }
    }

    public static void deleteAllToDo() {
        synchronized (toDoLS) {
            toDoLS.clear();
        }
    }
}
