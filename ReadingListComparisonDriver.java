/**
 * This class contains code which runs the reading list comparison feature.
 * 
 * Note: Throughout this feature's code, a "person" (such as person 1 or person 2) refers to one of the people whose reading lists we are comparing.
 * The "user" (such as in UserInterface) refers to the user of the book libary system application.
 * 
 * @author info-dev-1
 * @since 9/27/2025
 */
public class ReadingListComparisonDriver {

    // Run the reading list comparison feature.
    public static void main(String[] args) {

        // Load all essential data from the database.
        ReadingListLoader loader = new ReadingListLoader();
        loader.loadAllDataForComparisonFeature();

        // Access essential data for the analysis/comparison feature.
        String namePerson1 = loader.getNamePerson1();
        String namePerson2 = loader.getNamePerson2();

        Book[] bookArrayPerson1 = loader.getReadingListPerson1();
        Book[] bookArrayPerson2 = loader.getReadingListPerson2();

        // Perform the comparison.
        Controller controller = new Controller(namePerson1, bookArrayPerson1, namePerson2, bookArrayPerson2);
        controller.performComparison();
        
    }

}
