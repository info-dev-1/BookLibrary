import java.util.ArrayList;

/**
 * This class contains code which takes as input the ReadingListDataStore object. The result is to populate booksInCommon.
 * 
 * @author info-dev-1
 * @since 9/26/2025
 */
public class ReadingListAnalyzer {

    private ReadingListDataStore readingListDataStore;
    
    private ArrayList<Book> booksInCommon = new ArrayList<>();  // Books which are common to both people's reading lists.


    public ReadingListAnalyzer(ReadingListDataStore dataStore) {
        readingListDataStore = dataStore;
    }

    public ReadingListDataStore getReadingListDataStore() {
        return readingListDataStore;
    }

    public ArrayList<Book> getBooksInCommon() {
        return booksInCommon;
    }


    public void logBooksInCommon() {

        Book[] listPerson1 = getReadingListDataStore().getBookReadingListPerson1();
        Book[] listPerson2 = getReadingListDataStore().getBookReadingListPerson2();

        if (smallerListPerson1()) {
            // iterate through person 1's list
            for (int i = 0; i < listPerson1.length; i++) {
                if (listPerson1[i].equals(listPerson2[i])) {
                    getBooksInCommon().add(listPerson1[i]);
                }
            }
        } else {
            // iterate through person 2's list
            for (int i = 0; i < listPerson2.length; i++) {
                if (listPerson2[i].equals(listPerson1[i])) {
                    getBooksInCommon().add(listPerson2[i]);
                }
            }
        }
    }

    private boolean smallerListPerson1() {
        return getReadingListDataStore().getBookReadingListPerson1().length
            < getReadingListDataStore().getBookReadingListPerson2().length;
    }

}
