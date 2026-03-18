package TestToDoList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskManagerTest {

    private TaskManager manager;
    private User user1;
    private User user2;

    @Before
    public void setUp() {
        manager = new TaskManager();
        user1 = new User("Alice");
        user2 = new User("Bob");
    }

    @Test
    public void testAddUserSuccessfully() {
        manager.addUser(user1);
        assertEquals(user1, manager.findUser("Alice"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNullShouldThrowException() {
        manager.addUser(null);
    }

    @Test
    public void testFindUserWithDifferentCase() {
        manager.addUser(user1);
        User found = manager.findUser("aLiCe");
        assertNotNull(found);
        assertEquals(user1, found);
    }

    @Test
    public void testFindUserNotFoundShouldReturnNull() {
        manager.addUser(user1);
        User notFound = manager.findUser("Charlie");
        assertNull(notFound);
    }

    @Test
    public void testFindUserFromMultipleUsers() {
        manager.addUser(user1);
        manager.addUser(user2);

        assertEquals(user2, manager.findUser("Bob"));
    }
}
