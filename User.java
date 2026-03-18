package TestToDoList;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Task> tasks = new ArrayList<>();

    public User(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty");
        }
        this.name = name;
    }

    public String getName() { return name; }
    public List<Task> getTasks() { return tasks; }

    public void addTask(Task task) {
        if (task == null) throw new IllegalArgumentException("Task cannot be null");
        tasks.add(task);
    }

    public void removeTask(String title) {
        tasks.removeIf(t -> t.getTitle().equalsIgnoreCase(title));
    }
}
