package TestToDoList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    private Task task;

    @Before
    public void setUp() {
        task = new Task("Write unit tests");
    }

    @Test
    public void testTaskConstructorSetsTitleAndDefaultStatus() {
        assertEquals("Write unit tests", task.getTitle());
        assertFalse(task.isDone()); // ค่าเริ่มต้นต้องยังไม่เสร็จ
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTaskConstructorWithEmptyTitle() {
        new Task("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTaskConstructorWithNullTitle() {
        new Task(null);
    }

    @Test
    public void testMarkDoneSetsStatusToTrue() {
        task.markDone();
        assertTrue(task.isDone());
    }

    @Test
    public void testMarkUndoneSetsStatusToFalse() {
        task.markDone();      // ทำให้เป็น done ก่อน
        task.markUndone();    // แล้วเปลี่ยนกลับ
        assertFalse(task.isDone());
    }

    @Test
    public void testGetTitleReturnsCorrectTitle() {
        assertEquals("Write unit tests", task.getTitle());
    }
}
