import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;
    private Patron borrowedBy;
    private LocalDate dueDate; //Added for storing date

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
        this.borrowedBy = null;
        this.dueDate = null; // Add to initialize dueDate as null
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
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

    //Added LocalDate and DueDate
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    
    @Override
    public String toString() {
        if (available) {
            return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: Yes";
        } else {
            String dueDateStr = dueDate != null ? dueDate.toString() : "N/A"; // Added duedate string
            return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: No, Borrowed By: " + borrowedBy.getName() + ", Due Date: " + dueDateStr;
        }
    }
}
