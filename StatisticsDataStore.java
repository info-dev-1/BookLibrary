import java.util.ArrayList;

/**
 * This class holds computed statistics. Based on the reading lists, the statistics describe common interests to person 1 and person 2.
 * 
 * @author info-dev-1
 * @since 9/26/2025
 */
public class StatisticsDataStore {

    // Indexes for the statistics field.

    private static final int INDEX_GENRE_PREFERENCE_1 = 0;  // For accessing the genre preference for person 1.
    
    private static final int INDEX_GENRE_PREFERENCE_2 = 1;  // For accessing the genre preference for person 2.

    private static final int INDEX_PERCENTAGE_1 = 2;  // For accessing percentageBooks1ComparedTo2.

    private static final int INDEX_PERCENTAGE_2 = 3;  // For accessing percentageBooks2ComparedTo1.

    private static final int INDEX_RANDOM_COMMON_BOOK = 5;  // For accessing the random common book.

    private static final int INDEX_ZERO_BOOKS_IN_COMMON_FLAG = 4;  // For accessing the flag indicating 0 books in common to both peoples' lists.


    // Fields holding the statistics.

    private ArrayList<Object> statistics = null;  // The list of statistics.

    private Book randomBookInCommon = null;  // The random book in common to both person 1 and person 2.

    private int percentageBooks1ComparedTo2 = 0;  // Percentage of books on person 1's list that are also on person 2's list.

    private int percentageBooks2ComparedTo1 = 0;  // Percentage of books on person 2's list that are also on person 1's list.

    private GenrePreference genrePreferencePerson1 = null;  // Data indicating the genre "preference" for person 1.
    
    private GenrePreference genrePreferencePerson2 = null;  // Data indicating the genre "preference" for person 2.

    private boolean zeroBooksInCommonFlag = false;  // If this has been set to true, the app has detected that there are zero books in common to person 1's and person 2's lists.


    // Getters and setters.

    public static int getIndexGenrePreference1() {
        return INDEX_GENRE_PREFERENCE_1;
    }

    public static int getIndexGenrePreference2() {
        return INDEX_GENRE_PREFERENCE_2;
    }
    
    public static int getIndexPercentage1() {
        return INDEX_PERCENTAGE_1;
    }

    public static int getIndexPercentage2() {
        return INDEX_PERCENTAGE_2;
    }

    public static int getIndexRandomCommonBook() {
        return INDEX_RANDOM_COMMON_BOOK;
    }

    public static int getIndexZeroBooksInCommonFlag() {
        return INDEX_ZERO_BOOKS_IN_COMMON_FLAG;
    }

    public ArrayList<Object> getStatistics() {
        return statistics;
    }

    public void setStatistics(ArrayList<Object> statistics) {
        this.statistics = statistics;
    }

    public Book getRandomBookInCommon() {
        return randomBookInCommon;
    }

    public void setRandomBookInCommon(Book randomBookInCommon) {
        this.randomBookInCommon = randomBookInCommon;
    }

    public GenrePreference getGenrePreferencePerson1() {
        return genrePreferencePerson1;
    }

    public void setGenrePreferencePerson1(GenrePreference preference) {
        genrePreferencePerson1 = preference;
    }

    public GenrePreference getGenrePreferencePerson2() {
        return genrePreferencePerson2;
    }

    public void setGenrePreferencePerson2(GenrePreference preference) {
        genrePreferencePerson2 = preference;
    }

    public int getPercentageBooks1ComparedTo2() {
        return percentageBooks1ComparedTo2;
    }

    public void setPercentageBooks1ComparedTo2(int percentage) {
        this.percentageBooks1ComparedTo2 = percentage;
    }

    public int getPercentageBooks2ComparedTo1() {
        return percentageBooks2ComparedTo1;
    }

    public void setPercentageBooks2ComparedTo1(int percentage) {
        this.percentageBooks2ComparedTo1 = percentage;
    }

    public boolean getZeroBooksInCommonFlag() {
        return zeroBooksInCommonFlag;
    }

    public void setZeroBooksInCommonFlag(boolean flagValue) {
        zeroBooksInCommonFlag = flagValue;
    }
}
