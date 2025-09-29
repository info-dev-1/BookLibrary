import java.util.ArrayList;

/**
 * This class holds computed statistics. The statistics TODO: finish this.
 */
public class StatisticsDataStore {


    private static final int INDEX_GENRE_PREFERENCE_1 = 0;
    
    private static final int INDEX_GENRE_PREFERENCE_2 = 1;

    private static final int INDEX_PERCENTAGE_1 = 2;

    private static final int INDEX_PERCENTAGE_2 = 3;

    private static final int INDEX_RANDOM_COMMON_BOOK = 4;

    
    private ArrayList<Object> statistics = null;

    private Book randomBookInCommon = null;


    /** Data structures for holding computed statistics. */

    private int percentageBooks1ComparedTo2 = 0;  // Percentage of books on person 1's list that are also on person 2's list.

    private int percentageBooks2ComparedTo1 = 0;  // Percentage of books on person 2's list that are also on person 1's list.

    // Data indicating the genre "preference" for person 1. This will be either GenrePreference.FICTION or GenrePreference.NON_FICTION
    // This will be computed by seeing if person 1 has >= 60% of 1 genre in their list.
    private GenrePreference genrePreferencePerson1 = null;

    // Data indicating the genre "preference" for person 2. This will be either GenrePreference.FICTION or GenrePreference.NON_FICTION
    // This will be computed by seeing if person 2 has >= 60% of 1 genre in their list.
    private GenrePreference genrePreferencePerson2 = null;


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
}
