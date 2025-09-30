import java.util.ArrayList;

/**
 * This class handles output to the user.
 * If info-dev-1 codes the reading list input/assembling aspect, that code will be in this class.
 * 
 * When a field or method has the number 1 or the number 2, that refers to "person 1" or "person 2" (who are the people whose reading lists we are comparing).
 * 
 * @author info-dev-1
 * @since 9/23/2025
 */
public class UserInterface {
    
    // Names of the people whose reading lists we are comparing.

    private String namePerson1;

    private String namePerson2;


    public UserInterface(String namePerson1, String namePerson2) {
        this.namePerson1 = namePerson1;
        this.namePerson2 = namePerson2;
    }

    public String getNamePerson1() {
        return namePerson1;
    }

    public String getNamePerson2() {
        return namePerson2;
    }

    // Call methods to display all of the computed statistics.
    public void displayInterestStatistics(ArrayList<Object> statistics) {

        int indxGenre1 = StatisticsDataStore.getIndexGenrePreference1();
        int indxGenre2 = StatisticsDataStore.getIndexGenrePreference2();

        int indxPercentage1 = StatisticsDataStore.getIndexPercentage1();
        int indxPercentage2 = StatisticsDataStore.getIndexPercentage2();

        int indxRandomBook = StatisticsDataStore.getIndexRandomCommonBook();

        displayGenrePreferences( (GenrePreference)(statistics.get(indxGenre1)), (GenrePreference)(statistics.get(indxGenre2)) );

        displayCommonBookComparison( (Integer)(statistics.get(indxPercentage1)), (Integer)(statistics.get(indxPercentage2)) );

        // TODO: handle this if there are no books in common.
        displayRandomCommonBook( (Book)(statistics.get(indxRandomBook)) );
    }

    // Display genre preferences for both people.
    private void displayGenrePreferences(GenrePreference genrePreference1, GenrePreference genrePreference2) {
        
        System.out.println(getNamePerson1() + "'s genre preference is: " + genrePreference1.toString());
        System.out.println(getNamePerson2() + "'s genre preference is: " + genrePreference2.toString());
    }

    // Display percentages which compare books on the two reading lists.
    private void displayCommonBookComparison(Integer percentageBooks1ComparedTo2, Integer percentageBooks2ComparedTo1) {
        System.out.println(percentageBooks1ComparedTo2.toString()  
            + "% of " + getNamePerson1() + "'s books are also on " + getNamePerson2() + "'s list.");

        System.out.println(percentageBooks2ComparedTo1.toString()
            + "% of " + getNamePerson2() + "'s books are also on " + getNamePerson1() + "'s list.");
    }

    // Display a randomly-selected Book which is on both people's list.
    // TODO: handle this if there are no books in common.
    private void displayRandomCommonBook(Book randomCommonBook) {
        System.out.println(getNamePerson1() + " and " + getNamePerson2() + " have this book in common: ");
        System.out.println(randomCommonBook.toString());
    }
    
    
}
