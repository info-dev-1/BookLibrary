import java.util.ArrayList;

/**
 * This class contains code to process/perform the reading list comparison feature.
 * info-dev-1
 * 9/23/2025
 */
public class ReadingListComparisonProcessor {

    /** Reading lists. Note: The contained data are intentionally "repeated" from within the ReadingListComparisonDriver class. */

    private Book[] bookReadingListPerson1;  // The book reading list for person 1. This might be able to be declared final (I'll have to study that).

    private Book[] bookReadingListPerson2;  // The book reading list for person 2.


    /** Data structures for holding recorded data about shared interests. */

    private ArrayList<Book> booksInCommon = null;  // Books which are common to both people's reading lists.


    /** Data structures for holding computed statistics. */

    private double percentageBooks1ComparedTo2 = 0.0;  // Percentage of books on person 1's list that are also on person 2's list.

    private double percentageBooks2ComparedTo1 = 0.0;  // Percentage of books on person 2's list that are also on person 1's list.

    // Data indicating the genre "preference" for person 1. Either FICTION or NON-FICTION
    // This will be computed by seeing if person 1 has >= 60% of 1 genre in their list.
    private boolean genrePreference1 = false;

    // Data indicating the genre "preference" for person 2. Either FICTION or NON-FICTION
    // This will be computed by seeing if person 2 has >= 60% of 1 genre in their list.
    private boolean genrePreference2 = false;

    // TODO: decide whether to have Book class access these values, since I'll need a way to associate a genre with each book.
    private static final boolean FICTION = true;  // Value signaling the fiction genre of book. TODO: pot. amend "GENRE" if it makes more sense in context

    private static final boolean NON_FICTION = false;  // Value signaling the non-fiction genre of book. TODO: pot. amend "GENRE" if it makes more sense in context


    public ReadingListComparisonProcessor(Book[] bookReadingListPerson1, Book[] bookReadingListPerson2) {
        this.bookReadingListPerson1 = bookReadingListPerson1;
        this.bookReadingListPerson2 = bookReadingListPerson2;
    }


    public Book[] getBookReadingListPerson1() {
        return bookReadingListPerson1;
    }

    // no setter needed because the book list won't be changed after initialization.

    public Book[] getBookReadingListPerson2() {
        return bookReadingListPerson2;
    }

    public ArrayList<Book> getBooksInCommon() {
        return booksInCommon;
    }

    public void logComparisonBookReadingLists() {
        // compare books (decompose by writing a method, and call it here)
        logBooksInCommon();

        // compare genres (decompose by writing a method, and call it here)
    }

    private void logBooksInCommon() {
        if (smallerListPerson1()) {
            // iterate through person 1's list
            for (int i = 0; i < getBookReadingListPerson1().length; i++) {
                if (getBookReadingListPerson1()[i].equals(getBookReadingListPerson2()[i])) {
                    getBooksInCommon().add(getBookReadingListPerson1()[i]);
                }
            }
        } else {
            // iterate through person 2's list
            for (int i = 0; i < getBookReadingListPerson2().length; i++) {
                if (getBookReadingListPerson2()[i].equals(getBookReadingListPerson1()[i])) {
                    getBooksInCommon().add(getBookReadingListPerson2()[i]);
                }
            }
        }
    }

    private boolean smallerListPerson1() {
        return getBookReadingListPerson1().length < getBookReadingListPerson2().length;
    }

    private void computeSharedInterestStatistics() {
        // compute shared interest statistics, and store them in the appropriate fields.
    }

}