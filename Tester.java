/**
 * This class contains code which tests the code for the reading list comparison feature.
 * 
 * @author info-dev-1
 * @since 9/27/2025
 */
public class Tester {
    public static void main(String[] args) {

        Book book1 = new Book("Book 1 Title", "Book1 Author", "00001", Genre.FICTION);
        Book book2 = new Book("Book 2 Title", "Book2 Author", "00002", Genre.FICTION);
        Book book3 = new Book("Book 3 Title", "Book3 Author", "00003", Genre.FICTION);
        Book book4 = new Book("Book 4 Title", "Book4 Author", "00004", Genre.NON_FICTION);
        Book book5 = new Book("Book 5 Title", "Book5 Author", "00005", Genre.NON_FICTION);
        Book book6 = new Book("Book 6 Title", "Book6 Author", "00006", Genre.NON_FICTION);

        String aliceName = "Alice";
        String bobName = "Bob";

        Book[] aliceBooks = { book1, book2, book4 };
        Book[] bobBooks = { book3, book4, book2, book5 };

        Controller controller = new Controller(aliceName, aliceBooks, bobName, bobBooks);
        controller.performComparison();
        
    }

    // Note: I'm cancelling writing this method (on 9/28/25), and instead using the debugger to try to diagnose/fix the logBooksInCommon
    // and computePercentageBooks1ComparedTo2 (and its analog) methods.
    private void testLogBooksInCommon() {
        ReadingListDataStore listDataStore = new ReadingListDataStore(getAliceBooks(), getBobBooks());
        ReadingListAnalyzer analyzer = new ReadingListAnalyzer(listDataStore);

        
    }
    
    // Note: I'm cancelling writing this method (on 9/28/25), and instead using the debugger to try to diagnose/fix the logBooksInCommon
    // and computePercentageBooks1ComparedTo2 (and its analog) methods.
    private void TestComputePercentageBooks1ComparedTo2() {

    }

    private Book[] getAliceBooks() {

        Book book1 = new Book("Book 1 Title", "Book1 Author", "00001", Genre.FICTION);
        Book book2 = new Book("Book 2 Title", "Book2 Author", "00002", Genre.FICTION);
        Book book3 = new Book("Book 3 Title", "Book3 Author", "00003", Genre.FICTION);
        Book book4 = new Book("Book 4 Title", "Book4 Author", "00004", Genre.NON_FICTION);

        return new Book[] { book1, book2 };
    }

    private Book[] getBobBooks() {

        Book book1 = new Book("Book 1 Title", "Book1 Author", "00001", Genre.FICTION);
        Book book2 = new Book("Book 2 Title", "Book2 Author", "00002", Genre.FICTION);
        Book book3 = new Book("Book 3 Title", "Book3 Author", "00003", Genre.FICTION);
        Book book4 = new Book("Book 4 Title", "Book4 Author", "00004", Genre.NON_FICTION);

        return new Book[] { book3, book4, book1 };
    }


}
