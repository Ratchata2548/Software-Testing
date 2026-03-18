package LibraryTest;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Member(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty");
        }
        this.name = name;
    }

    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book cannot be null");
        book.borrow();
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnBook();
        }
    }
}
