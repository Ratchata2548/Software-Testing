package TestToDoList;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    private User user;
    private Task task1;
    private Task task2;

    @Before
    public void setUp() {
        user = new User("Alice");
        task1 = new Task("Buy groceries");
        task2 = new Task("Clean room");
    }

    @Test
    public void testUserConstructorAndGetName() {
        assertEquals("Alice", user.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserConstructorWithNullName() {
        new User(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserConstructorWithEmptyName() {
        new User("   ");
    }

    @Test
    public void testAddTask() {
        user.addTask(task1);
        user.addTask(task2);

        List<Task> tasks = user.getTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullTaskShouldThrowException() {
        user.addTask(null);
    }

    @Test
    public void testRemoveTaskByTitle() {
        user.addTask(task1);
        user.addTask(task2);

        user.removeTask("Buy groceries");

        List<Task> tasks = user.getTasks();
        assertEquals(1, tasks.size());
        assertFalse(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test
    public void testRemoveTaskCaseInsensitive() {
        user.addTask(new Task("Clean ROOM"));

        user.removeTask("clean room"); // lowercase

        assertTrue(user.getTasks().isEmpty());
    }

    @Test
    public void testRemoveTaskNotFound() {
        user.addTask(task1);

        user.removeTask("Nonexistent Task");

        // ไม่มีอะไรเปลี่ยน
        assertEquals(1, user.getTasks().size());
        assertTrue(user.getTasks().contains(task1));
    }

    @Test
    public void testGetTasksReturnsLiveList() {
        List<Task> tasks = user.getTasks();
        assertNotNull(tasks);

        // แก้ไขลิสต์โดยตรง (ไม่ควรทำแบบนี้ แต่ไว้ใช้ทดสอบ)
        tasks.add(task1);
        assertTrue(user.getTasks().contains(task1));
    }
}
