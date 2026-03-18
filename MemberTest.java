package LibraryTest;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MemberTest {

    private Member member;
    private Book book;

    @Before
    public void setUp() {
        member = new Member("John Doe");
        book = new Book("Refactoring", "Martin Fowler");
    }

    @Test
    public void testMemberConstructorAndName() {
        assertEquals("John Doe", member.getName());
        assertTrue(member.getBorrowedBooks().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMemberConstructorWithEmptyName() {
        new Member("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMemberConstructorWithNullName() {
        new Member(null);
    }

    @Test
    public void testBorrowBook() {
        member.borrowBook(book);

        List<Book> borrowedBooks = member.getBorrowedBooks();
        assertEquals(1, borrowedBooks.size());
        assertTrue(borrowedBooks.contains(book));
        assertTrue(book.isBorrowed());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBorrowBookWithNull() {
        member.borrowBook(null);
    }

    @Test
    public void testReturnBook() {
        member.borrowBook(book);
        assertTrue(book.isBorrowed());

        member.returnBook(book);

        assertFalse(book.isBorrowed());
        assertFalse(member.getBorrowedBooks().contains(book));
    }

    @Test
    public void testReturnBookNotBorrowed() {
        // ไม่ได้ยืม แต่เรียกคืน -> ไม่ควร error
        member.returnBook(book);

        assertFalse(book.isBorrowed());
        assertTrue(member.getBorrowedBooks().isEmpty());
    }
}
