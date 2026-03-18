package TestToDoList;

public class Task {
    private String title;
    private boolean done;

    public Task(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        this.title = title;
        this.done = false;
    }

    public String getTitle() { return title; }
    public boolean isDone() { return done; }

    public void markDone() { this.done = true; }
    public void markUndone() { this.done = false; }
}
