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

    private boolean zeroBooksInCommonFlag = false;  // If this has been set to true, the app has detected that there are zero books in common to person 1's and person 2's lists.


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

    // Getter for zeroBooksInCommonFlag.
    public boolean zeroBooksInCommon() {
        return zeroBooksInCommonFlag;
    }

    public void setZeroBooksInCommonFlag(boolean flagValue) {
        zeroBooksInCommonFlag = flagValue;
    }

    // Call methods to display all of the computed statistics.
    public void displayInterestStatistics(ArrayList<Object> statistics) {

        displayComparisonFeatureHeader();

        int indxGenre1 = StatisticsDataStore.getIndexGenrePreference1();
        int indxGenre2 = StatisticsDataStore.getIndexGenrePreference2();

        int indxPercentage1 = StatisticsDataStore.getIndexPercentage1();
        int indxPercentage2 = StatisticsDataStore.getIndexPercentage2();

        int indxZeroBooksInCommonFlag = StatisticsDataStore.getIndexZeroBooksInCommonFlag();

        displayGenrePreferences( (GenrePreference)(statistics.get(indxGenre1)), (GenrePreference)(statistics.get(indxGenre2)) );

        boolean zeroBooksInCommon = (Boolean)statistics.get(indxZeroBooksInCommonFlag);
        if (zeroBooksInCommon) {
            displayZeroBooksInCommonNotification();
        }
        else {
            displayCommonBookComparison( (Integer)(statistics.get(indxPercentage1)), (Integer)(statistics.get(indxPercentage2)) );

            int indxRandomBook = StatisticsDataStore.getIndexRandomCommonBook();
            displayRandomCommonBook( (Book)(statistics.get(indxRandomBook)) );
        }
        
    }

    private void displayComparisonFeatureHeader() {
        System.out.println();
        System.out.println("Book Library System: Analyze/Compare Two Peoples' Personal Reading Lists");
        System.out.println("-------------------------------------------------------------------------------------");
  
    }

    // Display genre preferences for both people.
    private void displayGenrePreferences(GenrePreference genrePreference1, GenrePreference genrePreference2) {
        String person1GenrePreferenceString = computeGenrePreferenceString(genrePreference1);
        String person2GenrePreferenceString = computeGenrePreferenceString(genrePreference2);

        System.out.println();
        System.out.println(getNamePerson1() + "'s genre preference is: " + person1GenrePreferenceString);
        System.out.println(getNamePerson2() + "'s genre preference is: " + person2GenrePreferenceString);
    }

    private String computeGenrePreferenceString(GenrePreference genrePreferenceEnum) {
        
        if (genrePreferenceEnum.equals(GenrePreference.FICTION)) {
            return "fiction";
        }
        else if (genrePreferenceEnum.equals(GenrePreference.NON_FICTION)) {
            return "nonfiction";
        }
        else {
            return "no particular genre";
        }
    }

    // Display percentages which compare books on the two reading lists.
    private void displayCommonBookComparison(Integer percentageBooks1ComparedTo2, Integer percentageBooks2ComparedTo1) {
        System.out.println();
        System.out.println(percentageBooks1ComparedTo2.toString()  
            + "% of " + getNamePerson1() + "'s books are also on " + getNamePerson2() + "'s list.");

        System.out.println(percentageBooks2ComparedTo1.toString()
            + "% of " + getNamePerson2() + "'s books are also on " + getNamePerson1() + "'s list.");
    }

    private void displayZeroBooksInCommonNotification() {
        System.out.println();
        System.out.println(getNamePerson1() + " and " + getNamePerson2() + " have no books in common to their reading lists.");
        System.out.println();
    }

    // Display a randomly-selected Book which is on both people's list.
    private void displayRandomCommonBook(Book randomCommonBook) {
        System.out.println();
        System.out.println(getNamePerson1() + " and " + getNamePerson2() + " have this book in common: ");
        System.out.println(randomCommonBook.toString());
        System.out.println();
    }


    
    
}
