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

    private double percentageBooks1ComparedTo2 = 0.0;  // Percentage of books on person 1's list that are also on person 2's list.

    private double percentageBooks2ComparedTo1 = 0.0;  // Percentage of books on person 2's list that are also on person 1's list.

    // Data indicating the genre "preference" for person 1. This will be either Genre.FICTION or Genre.NON_FICTION
    // This will be computed by seeing if person 1 has >= 60% of 1 genre in their list.
    private Genre genrePreference1 = null;

    // Data indicating the genre "preference" for person 2. This will be either Genre.FICTION or Genre.NON_FICTION
    // This will be computed by seeing if person 2 has >= 60% of 1 genre in their list.
    private Genre genrePreference2 = null;


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

    public double getPercentageBooks1ComparedTo2() {
        return percentageBooks1ComparedTo2;
    }

    public void setPercentageBooks1ComparedTo2(double percentage) {
        this.percentageBooks1ComparedTo2 = percentage;
    }

    public double getPercentageBooks2ComparedTo1() {
        return percentageBooks2ComparedTo1;
    }

    public void setPercentageBooks2ComparedTo1(double percentage) {
        this.percentageBooks2ComparedTo1 = percentage;
    }

    // TODO: decide whether to use this or delete it.
    public void unpackReadingInterestStatistics() {
        
        setGenrePreference1((Genre)getStatistics().get(INDEX_GENRE_PREFERENCE_1));
        setGenrePreference2((Genre)getStatistics().get(INDEX_GENRE_PREFERENCE_2));

        setPercentageBooks1ComparedTo2((Double)getStatistics().get(INDEX_PERCENTAGE_1));
        setPercentageBooks2ComparedTo1((Double)getStatistics().get(INDEX_PERCENTAGE_2));

        setRandomBookInCommon((Book)getStatistics().get(INDEX_RANDOM_COMMON_BOOK));
    }
}
