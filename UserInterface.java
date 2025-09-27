import java.util.ArrayList;

/**
 * This class handles input from the user and output to the user.
 * When a field or method has the number 1 or the number 2, that refers to "person 1" or "person 2" (who are the people whose reading lists we are comparing).
 * 
 * info-dev-1
 * 9/23/2025
 */
public class UserInterface {
    
    
    private static final int INDEX_GENRE_PREFERENCE_1 = 0;
    
    private static final int INDEX_GENRE_PREFERENCE_2 = 1;

    private static final int INDEX_PERCENTAGE_1 = 2;

    private static final int INDEX_PERCENTAGE_2 = 3;

    private static final int INDEX_RANDOM_COMMON_BOOK = 4;

    private String namePerson1;

    private String namePerson2;

    
    Genre genrePreference1 = null;

    Genre genrePreference2 = null;

    private Book randomBookInCommon = null;


    /** Data structures for holding computed statistics. */

    private double percentageBooks1ComparedTo2 = 0.0;  // Percentage of books on person 1's list that are also on person 2's list.

    private double percentageBooks2ComparedTo1 = 0.0;

    public UserInterface(String namePerson1, String namePerson2) {
        this.namePerson1 = namePerson1;
        this.namePerson2 = namePerson2;
    }

    public Book getRandomBookInCommon() {
        return randomBookInCommon;
    }

    public void setRandomBookInCommon(Book randomBookInCommon) {
        this.randomBookInCommon = randomBookInCommon;
    }

    public Genre getGenrePreference1() {
        return genrePreference1;
    }

    public void setGenrePreference1(Genre preference) {
        genrePreference1 = preference;
    }

    public Genre getGenrePreference2() {
        return genrePreference2;
    }

    public void setGenrePreference2(Genre preference) {
        genrePreference2 = preference;
    }

    private double getPercentageBooks1ComparedTo2() {
        return percentageBooks1ComparedTo2;
    }

    private void setPercentageBooks1ComparedTo2(double percentage) {
        this.percentageBooks1ComparedTo2 = percentage;
    }

    private double getPercentageBooks2ComparedTo1() {
        return percentageBooks2ComparedTo1;
    }

    private void setPercentageBooks2ComparedTo1(double percentage) {
        this.percentageBooks2ComparedTo1 = percentage;
    }


    public void unpackInterestResults(ArrayList<Object> interestResults) {

        // Unpack the reading interest results.
        setGenrePreference1((Genre)interestResults.get(INDEX_GENRE_PREFERENCE_1));
        setGenrePreference2((Genre)interestResults.get(INDEX_GENRE_PREFERENCE_2));

        setPercentageBooks1ComparedTo2((Double)interestResults.get(INDEX_PERCENTAGE_1));
        setPercentageBooks2ComparedTo1((Double)interestResults.get(INDEX_PERCENTAGE_2));

        setRandomBookInCommon((Book)interestResults.get(INDEX_RANDOM_COMMON_BOOK));
    }

    public void displayInterestResults() {
        // displayGenrePreferences(getGenrePreference1(), getGenrePreference2());
        // displayCommonBookComparison(getPercentageBooks1ComparedTo2(), getPercentageBooks2ComparedTo1());
        // displayRandomCommonBook(getRandomBookInCommon());
    }

    
    
}
