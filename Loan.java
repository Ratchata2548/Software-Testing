package LibraryTest;

import java.time.LocalDate;

public class Loan {
    private Book book;
    private Member member;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Loan(Book book, Member member) {
        if (book == null || member == null) {
            throw new IllegalArgumentException("Book and Member cannot be null");
        }
        this.book = book;
        this.member = member;
        this.borrowDate = LocalDate.now();
    }

    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        member.returnBook(book);
    }
}
