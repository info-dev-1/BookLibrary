import java.util.ArrayList;

/**
 * This class contains code to process/perform the reading list comparison feature.
 * info-dev-1
 * 9/23/2025
 */
public class ReadingListComparisonProcessor {

    /** Reading lists. Note: The contained data are intentionally "repeated" from within the ReadingListComparisonDriver class. */

    private Book[] bookReadingListPerson1;  // The book reading list for person 1. 

    private Book[] bookReadingListPerson2;  // The book reading list for person 2.


    /** Data structures for holding recorded data about shared interests. */

    private ArrayList<Book> booksInCommon = null;  // Books which are common to both people's reading lists.

    // Authors which are common to both people's reading lists. In a simple format: "First name, Last name"
    private ArrayList<String> authorsInCommon = null;


    /** Data structures for holding computed statistics. */

    private double percentageBooks1ComparedTo2 = 0.0;  // Percentage of books on person 1's list that are also on person 2's list.

    private double percentageAuthors1ComparedTo2 = 0.0;  // Percentage of authors on person 1's list that are also on person 2's list.

    private double percentageBooks2ComparedTo1 = 0.0;  // Percentage of books on person 2's list that are also on person 1's list.

    private double percentageAuthors2ComparedTo1 = 0.0;  // Percentage of authors on person 2's list that are also on person 1's list.

    // Data indicating the genre "preference" for person 1. Either FICTION or NON-FICTION
    // This will be computed by seeing if person 1 has >= 60% of 1 genre in their list.
    private boolean genrePreference1 = false;

    // Data indicating the genre "preference" for person 2. Either FICTION or NON-FICTION
    // This will be computed by seeing if person 2 has >= 60% of 1 genre in their list.
    private boolean genrePreference2 = false;

    private static final boolean FICTION = true;  // Value signaling the fiction genre of book. TODO: pot. amend "GENRE" if it makes more sense in context

    private static final boolean NON_FICTION = false;  // Value signaling the non-fiction genre of book. TODO: pot. amend "GENRE" if it makes more sense in context


    public ReadingListComparisonProcessor(ArrayList<Book> readingListPerson1, ArrayList<Book> readingListPerson2) {
        this.bookReadingListPerson1 = bookReadingArrayListToArray(readingListPerson1);
        this.bookReadingListPerson2 = bookReadingArrayListToArray(readingListPerson2);
    }

    private Book[] bookReadingArrayListToArray(ArrayList<Book> inputArrayList) {
        Book[] result = new Book[inputArrayList.size()];
        for (int i = 0; i < inputArrayList.size(); i++) {
            result[i] = inputArrayList.get(i);
        }
        return result;
    }
}