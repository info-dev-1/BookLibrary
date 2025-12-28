/**
 * This class holds two reading lists, one for each of the people.
 * 
 * @author info-dev-1
 * @since 9/27/2025
 */
public class ReadingListDataStore {
    
    private Book[] bookReadingListPerson1;  // The book reading list for person 1. This might be able to be declared final (I'll have to study that).

    private Book[] bookReadingListPerson2;  // The book reading list for person 2.

    
    public ReadingListDataStore(Book[] bookReadingListPerson1, Book[] bookReadingListPerson2) {
        this.bookReadingListPerson1 = bookReadingListPerson1;
        this.bookReadingListPerson2 = bookReadingListPerson2;
    }

    // No setters are needed, because the book lists won't be changed after their initialization.

    public Book[] getBookReadingListPerson1() {
        return bookReadingListPerson1;
    }

    public Book[] getBookReadingListPerson2() {
        return bookReadingListPerson2;
    }


}
