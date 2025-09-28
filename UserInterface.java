import java.util.ArrayList;

/**
 * This class handles input from the user and output to the user.
 * When a field or method has the number 1 or the number 2, that refers to "person 1" or "person 2" (who are the people whose reading lists we are comparing).
 * 
 * info-dev-1
 * 9/23/2025
 */
public class UserInterface {
    
    private String namePerson1;

    private String namePerson2;


    public UserInterface(String namePerson1, String namePerson2) {
        this.namePerson1 = namePerson1;
        this.namePerson2 = namePerson2;
    }


    public void displayInterestStatistics(ArrayList<Object> statistics) {

        int indxGenre1 = StatisticsDataStore.getIndexGenrePreference1();
        int indxGenre2 = StatisticsDataStore.getIndexGenrePreference2();

        int indxPercentage1 = StatisticsDataStore.getIndexPercentage1();
        int indxPercentage2 = StatisticsDataStore.getIndexPercentage2();

        int indxRandomBook = StatisticsDataStore.getIndexRandomCommonBook();

        displayGenrePreferences( (Genre)(statistics.get(indxGenre1)), (Genre)(statistics.get(indxGenre2)) );

        displayCommonBookComparison( (Double)(statistics.get(indxPercentage1)), (Double)(statistics.get(indxPercentage2)) );

        displayRandomCommonBook( (Book)(statistics.get(indxRandomBook)) );
    }

    private void displayGenrePreferences(Genre genrePreference1, Genre genrePreference2) {
        // TODO: finish this method. use println statements.
    }

    private void displayCommonBookComparison(Double percentageBooks1ComparedTo2, Double percentageBooks2ComparedTo1) {
        // TODO: finish this
    }

    private void displayRandomCommonBook(Book randomCommonBook) {
        // TODO: finish this
    }
    
    
}
