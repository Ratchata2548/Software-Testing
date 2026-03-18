package LibraryTest;

public class Book {
    private String title;
    private String author;
    private boolean borrowed;

    public Book(String title, String author) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        this.title = title;
        this.author = author;
        this.borrowed = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isBorrowed() { return borrowed; }

    public void borrow() {
        if (borrowed) {
            throw new IllegalStateException("Book already borrowed");
        }
        borrowed = true;
    }

    public void returnBook() {
        borrowed = false;
    }
}
