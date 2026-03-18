package LibraryTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BookTest {

    private Book book;

    @Before
    public void setUp() {
        // ทำการสร้าง Book ใหม่ทุกครั้งก่อนการทดสอบ
        book = new Book("Effective Java", "Joshua Bloch");
    }

    @Test
    public void testBookConstructor() {
        // ตรวจสอบค่าที่ถูกสร้างขึ้นมาผ่าน Constructor
        assertEquals("Effective Java", book.getTitle());
        assertEquals("Joshua Bloch", book.getAuthor());
        assertFalse(book.isBorrowed());  // หนังสือยังไม่ได้ยืม
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBookConstructorWithEmptyTitle() {
        // ทดสอบกรณีที่สร้าง Book โดยไม่มีชื่อหนังสือ
        new Book("", "Author");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBookConstructorWithNullTitle() {
        // ทดสอบกรณีที่สร้าง Book โดยชื่อหนังสือเป็น null
        new Book(null, "Author");
    }

    @Test
    public void testBookConstructorWithValidTitle() {
        // ทดสอบกรณีที่สร้างหนังสือด้วยชื่อที่ถูกต้อง
        Book validBook = new Book("Clean Code", "Robert C. Martin");
        assertEquals("Clean Code", validBook.getTitle());
        assertEquals("Robert C. Martin", validBook.getAuthor());
        assertFalse(validBook.isBorrowed());
    }

    @Test
    public void testBorrowBook() {
        // ทดสอบการยืมหนังสือ
        book.borrow();
        assertTrue(book.isBorrowed());
    }

    @Test(expected = IllegalStateException.class)
    public void testBorrowBookWhenAlreadyBorrowed() {
        // ทดสอบกรณีที่พยายามยืมหนังสือที่ยืมไปแล้ว
        book.borrow();
        book.borrow();  // ควรจะเกิด IllegalStateException
    }

    @Test
    public void testReturnBook() {
        // ทดสอบการคืนหนังสือ
        book.borrow(); // ยืมหนังสือก่อน
        book.returnBook();
        assertFalse(book.isBorrowed());  // ควรจะคืนหนังสือแล้ว
    }

    @Test
    public void testReturnBookWhenNotBorrowed() {
        // ทดสอบการคืนหนังสือที่ไม่ได้ถูกยืม
        book.returnBook();  // คืนหนังสือในขณะที่ไม่ได้ยืม
        assertFalse(book.isBorrowed());  // ควรจะไม่เปลี่ยนแปลงสถานะ
    }

    @Test(expected = IllegalStateException.class)
    public void testReturnBookAfterAlreadyReturned() {
        // ทดสอบกรณีคืนหนังสือซ้ำหลังจากที่คืนแล้ว
        book.borrow();
        book.returnBook();
        book.returnBook();  // ควรจะเกิด IllegalStateException
    }
}
