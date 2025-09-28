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

        if (largerListPerson1()) {
            
            for (int i = 0; i < listPerson1.length; i++) {
                for (int j = 0; j < listPerson2.length; j++) {
                    
                    logBookIfNecessary(listPerson1, i, listPerson2, j);
                }
            }
        }
        else if (largerListPerson2()) {

            for (int i = 0; i < listPerson2.length; i++) {
                for (int j = 0; j < listPerson1.length; j++) {
                    
                    logBookIfNecessary(listPerson2, i, listPerson1, j);
                }
            }
        }
    }

    private void logBookIfNecessary(Book[] listFirstPerson, int indxListFirstPerson, Book[] listSecondPerson, int indxListSecondPerson) {
        int k = indxListFirstPerson;
        int m = indxListSecondPerson;

        if (!bookPreviouslyAdded(listSecondPerson[m], getBooksInCommon()) 
            && bookInCommon(listFirstPerson[k], listSecondPerson[m])) {

            getBooksInCommon().add(listSecondPerson[m]);
        }
    }


    private boolean largerListPerson1() {
        return getReadingListDataStore().getBookReadingListPerson1().length
            > getReadingListDataStore().getBookReadingListPerson2().length;
    }

    private boolean largerListPerson2() {
        return getReadingListDataStore().getBookReadingListPerson2().length
            > getReadingListDataStore().getBookReadingListPerson1().length;
    }

    private boolean bookPreviouslyAdded(Book bookToCheck, ArrayList<Book> booksInCommon) {
        for (Book bookInCommon : booksInCommon) {
            if (bookToCheck.equals(bookInCommon)) {
                return true;
            }
        }
        return false;
    }

    private boolean bookInCommon(Book book1, Book book2) {
        return book1.equals(book2);
    }
}
