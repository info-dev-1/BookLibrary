/**
 * This class contains code which tests the reading list comparison feature.
 * 
 * Note: Throughout this feature's code, a "person" (such as person 1 or person 2) refers to one of the people whose reading lists we are comparing.
 * The "user" (such as in UserInterface) refers to the user of the book libary system application.
 * 
 * @author info-dev-1
 * @since 9/27/2025
 */
public class Tester {

    // Test the reading comparison feature.
    public static void main(String[] args) {

        // ReadingListLoader testLoader = new ReadingListLoader();
        // testLoader.testingUpdateFruitsTable();
        

        // Arrange variables for testing.

        // Fiction books.
        Book theLordOfTheRings = new Book("The Lord of the Rings", "J.R.R. Tolkien", "47841", Genre.FICTION);
        Book harryPotter = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "72050", Genre.FICTION);
        Book theMartian = new Book("The Martian", "Andy Weir", "05013", Genre.FICTION);
        Book hitchhikersGuide = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "66302", Genre.FICTION);

        // Non-fiction books.
        Book goodCodeBadCode = new Book("Good Code, Bad Code", "Tom Long", "33198", Genre.NON_FICTION);
        Book gitForProgrammers = new Book("Git for Programmers", "Jesse Liberty", "35772", Genre.NON_FICTION);
        Book statisticsBook = new Book("Statistics", "James McClave, Terry Sincich", "19453", Genre.NON_FICTION);

        String aliceName = "Alice";
        String bobName = "Bob";

        Book[] aliceBooks = { theLordOfTheRings, theMartian, harryPotter, hitchhikersGuide, gitForProgrammers };
        Book[] bobBooks = { hitchhikersGuide, goodCodeBadCode, gitForProgrammers, statisticsBook };

        // Perform the test.
        Controller controller = new Controller(aliceName, aliceBooks, bobName, bobBooks);
        controller.performComparison();
        
    }

}
