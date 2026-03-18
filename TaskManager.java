package TestToDoList;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        users.add(user);
    }

    public User findUser(String name) {
        return users.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
