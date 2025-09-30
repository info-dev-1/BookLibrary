/**
 * This class contains code which tests the reading list comparison feature.
 * 
 * @author info-dev-1
 * @since 9/27/2025
 */
public class Tester {

    // Test the reading comparison feature.
    public static void main(String[] args) {

        // Arrange variables for testing.
        Book book1 = new Book("Book 1 Title", "Book1 Author", "00001", Genre.FICTION);
        Book book2 = new Book("Book 2 Title", "Book2 Author", "00002", Genre.FICTION);
        Book book3 = new Book("Book 3 Title", "Book3 Author", "00003", Genre.FICTION);
        Book book4 = new Book("Book 4 Title", "Book4 Author", "00004", Genre.NON_FICTION);
        Book book5 = new Book("Book 5 Title", "Book5 Author", "00005", Genre.NON_FICTION);
        Book book6 = new Book("Book 6 Title", "Book6 Author", "00006", Genre.NON_FICTION);

        String aliceName = "Alice";
        String bobName = "Bob";

        Book[] aliceBooks = { book1, book2, book4 };
        Book[] bobBooks = { book3, book4, book2, book5, book6 };

        // Perform the test.
        Controller controller = new Controller(aliceName, aliceBooks, bobName, bobBooks);
        controller.performComparison();
        
    }

}
