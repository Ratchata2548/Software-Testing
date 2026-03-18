package LibraryTest;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;
    private Member member;

    @Before
    public void setUp() {
        library = new Library("My Library");

        // สร้างหนังสือ
        book1 = new Book("Clean Code", "Robert C. Martin");
        book2 = new Book("Effective Java", "Joshua Bloch");

        // เพิ่มหนังสือเข้าไปในห้องสมุด
        library.addBook(book1);
        library.addBook(book2);

        // สร้างสมาชิก (ต้องมีคลาส Member ที่มีเมธอด borrowBook(Book))
        member = new Member("John Doe");
    }

    @Test
    public void testAddBook() {
        List<Book> books = library.getBooks();
        assertEquals(2, books.size());
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
    }

    @Test
    public void testBorrowBook() {
        Loan loan = library.borrowBook("Clean Code", member);

        assertNotNull(loan);
        assertEquals(book1, loan.getBook());
        assertEquals(member, loan.getMember());
        assertTrue(book1.isBorrowed());
    }

    @Test(expected = IllegalStateException.class)
    public void testBorrowUnavailableBook() {
        // ยืมหนังสือก่อน
        library.borrowBook("Clean Code", member);
        // พยายามยืมอีกรอบ -> ควร Error
        library.borrowBook("Clean Code", new Member("Another User"));
    }

    @Test
    public void testReturnBook() {
        Loan loan = library.borrowBook("Effective Java", member);
        assertTrue(book2.isBorrowed());

        library.returnBook(book2, member);
        assertFalse(book2.isBorrowed());
    }

    @Test
    public void testGetAvailableBookCount() {
        assertEquals(2, library.getAvailableBookCount());

        library.borrowBook("Clean Code", member);
        assertEquals(1, library.getAvailableBookCount());

        library.returnBook(book1, member);
        assertEquals(2, library.getAvailableBookCount());
    }
}
