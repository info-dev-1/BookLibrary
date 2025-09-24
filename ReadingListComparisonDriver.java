import java.util.ArrayList;

/**
 * This class contains code to test or demonstrate the reading list comparison feature.
 * info-dev-1
 * 9/23/2025
 */
public class ReadingListComparisonDriver {


    /** Reading lists. I decided to keep these here because they are a little bit higher-level than the Processor class.
     * Data from these lists will be somehow passed to the Processor for processing.
     * Note: data from these will also be kept in the Processor class as array instance fields.
    */
    
    private Book[] bookReadingListPerson1;  // The reading list for person 1.
    
    private Book[] bookReadingListPerson2;  // The reading list for person 2.

    private ReadingListComparisonProcessor processor;

    private ReadingListComparisonUserInterface userInterface;


    public ReadingListComparisonDriver(Book[] bookReadingListPerson1, Book[] bookReadingListPerson2) {
        this.processor = new ReadingListComparisonProcessor(bookReadingListPerson1, bookReadingListPerson2);
        this.userInterface = new ReadingListComparisonUserInterface();
    }

    // could be renamed to something like, runComparison.
    // TODO: if I get the book reading lists from the user, then I'll probably have to use an ArrayList (somwhere*) b/c we don't know how many books they'll add. *Maybe in the UserInterface class, so I don't have to "repeat myself" by passing ArrayLists to two class constructors. 
    public static void main(String[] args) {

        Book[] bookReadingListPerson1 = getUserInterface().getPerson1BookReadingListFromUser();
        Book[] bookReadingListPerson2 = getUserInterface().getPerson2BookReadingListFromUser();
        ReadingListComparisonDriver driver = new ReadingListComparisonDriver(bookReadingListPerson1, bookReadingListPerson2);

        driver.getProcessor().logComparisonBookReadingLists();  // TODO: decide whether to change this to logBooksInCommon() (doing 1 thing), or keep it (doing 2+ things).
        driver.getProcessor().computeSharedInterestStatistics();
        
        driver.getUserInterface().displaySharedInterestResults();
    }
    


