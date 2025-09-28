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


        String aliceName = "Alice";
        String bobName = "Bob";

        Book[] aliceBooks = { book1, book2 };
        Book[] bobBooks = { book3, book4 };

        Controller controller = new Controller(aliceName, aliceBooks, bobName, bobBooks);
        controller.performComparison();
        
    }

}
