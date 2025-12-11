import java.time.LocalDate;

/**
 * This class represents a book.
 * This class was present in the original, forked codebase.
 */
public class Book {

    // Fields.

    private String title;
    private String author;
    private String isbn;
    private Genre genre;
    private boolean available;
    private Patron borrowedBy;
    private LocalDate dueDate; // Added for storing date.

    public Book(String title, String author, String isbn, Genre genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.available = true;
        this.borrowedBy = null;
        this.dueDate = null; // Add to initialize dueDate as null.
    }

    // Getters and setters.

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public Patron getBorrowedBy() {
        return borrowedBy;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBorrowedBy(Patron borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    // Added LocalDate and DueDate.
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Return the String representation of this book.
    @Override
    public String toString() {
        if (available) {
            return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: Yes";
        } else {
            String dueDateStr = dueDate != null ? dueDate.toString() : "N/A";  // Added duedate string.
            return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: No, Borrowed By: " + borrowedBy.getName() + ", Due Date: " + dueDateStr;
        }
    }
}
