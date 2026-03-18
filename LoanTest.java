package LibraryTest;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LoanTest {

    private Book book;
    private Member member;
    private Loan loan;

    @Before
    public void setUp() {
        // สร้างออบเจ็กต์จำเป็นสำหรับ Loan
        book = new Book("Test-Driven Development", "Kent Beck");
        member = new Member("Alice");

        loan = new Loan(book, member);
    }

    @Test
    public void testLoanConstructorSetsFieldsCorrectly() {
        assertEquals(book, loan.getBook());
        assertEquals(member, loan.getMember());
        assertEquals(LocalDate.now(), loan.getBorrowDate());
        assertNull(loan.getReturnDate());
    }

    @Test
    public void testReturnBookSetsReturnDate() {
        assertNull(loan.getReturnDate());

        loan.returnBook();

        assertNotNull(loan.getReturnDate());
        assertEquals(LocalDate.now(), loan.getReturnDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoanConstructorWithNullBook() {
        new Loan(null, member);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoanConstructorWithNullMember() {
        new Loan(book, null);
    }
}
