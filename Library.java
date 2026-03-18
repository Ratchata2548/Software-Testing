package LibraryTest;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> books = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    public Library(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Library name cannot be empty");
        }
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Loan borrowBook(String title, Member member) {
        Book book = books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title) && !b.isBorrowed())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Book not available"));

        member.borrowBook(book);
        Loan loan = new Loan(book, member);
        loans.add(loan);
        return loan;
    }

    public void returnBook(Book book, Member member) {
        loans.stream()
                .filter(l -> l.getBook().equals(book) && l.getMember().equals(member) && l.getReturnDate() == null)
                .findFirst()
                .ifPresent(Loan::returnBook);
    }

    public int getAvailableBookCount() {
        return (int) books.stream().filter(b -> !b.isBorrowed()).count();
    }
}
