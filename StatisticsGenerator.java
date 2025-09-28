import java.util.ArrayList;

/**
 * This class contains code which generates statistics. The statistics relate to interests/shared interests between the two people whose lists we are comparing.
 * 
 * @author info-dev-1
 * @since 9/26/2025
 */

public class StatisticsGenerator {
    
    private static final double PREFERENCE_LEVEL_PERCENTAGE = 60.0;

    private ReadingListDataStore readingListDataStore;

    private ArrayList<Book> booksInCommon;  // Books which are common to both people's reading lists.

    private StatisticsDataStore statisticsDataStore;

    public StatisticsGenerator(ReadingListDataStore listDataStore, ArrayList<Book> booksInCommon, StatisticsDataStore statsDataStore) {
        this.readingListDataStore = listDataStore;
        this.booksInCommon = booksInCommon;
        this.statisticsDataStore = statsDataStore;
    }

    public ReadingListDataStore getReadingListDataStore() {
        return readingListDataStore;
    }

    public ArrayList<Book> getBooksInCommon() {
        return booksInCommon;
    }

    public void setBooksInCommon(ArrayList<Book> booksInCommon) {
        this.booksInCommon = booksInCommon;
    }

    public StatisticsDataStore getStatisticsDataStore() {
        return statisticsDataStore;
    }

    public void computeInterestStatistics() {
        // compute interest statistics, and store them in the appropriate fields.
        computeGenrePreference1();
        computeGenrePreference2();
        
        computePercentageBooks1ComparedTo2();
        computePercentageBooks2ComparedTo1();

        pickRandomBookInCommon();
    }

    // Requires that bookReadingListPerson1 is not empty. TODO: If it is - how to deal with that exception?
    private void computeGenrePreference1() {
        
        Book[] listPerson1 = getReadingListDataStore().getBookReadingListPerson1();

        int countFictionBooks = 0;
        // int countNonFictionBooks = 0;

        for (Book book : listPerson1) {
            if (book.getGenre().equals(Genre.FICTION)) {  
                countFictionBooks++;
            }
            // else if (book.getGenre().equals(NON_FICTION)) {
            //     countNonFictionBooks++;
            // }       
        }
        if (((double)countFictionBooks / listPerson1.length) >= PREFERENCE_LEVEL_PERCENTAGE) {
            getStatisticsDataStore().setGenrePreference1(Genre.FICTION);
        } else {
            getStatisticsDataStore().setGenrePreference1(Genre.NON_FICTION);
        }
    }

    // Requires that bookReadingListPerson2 is not empty. TODO: If it is - how to deal with that exception?
    private void computeGenrePreference2() {

        Book[] listPerson2 = getReadingListDataStore().getBookReadingListPerson2();

        int countFictionBooks = 0;
        // int countNonFictionBooks = 0;

        for (Book book : listPerson2) {
            if (book.getGenre().equals(Genre.FICTION)) { 
                countFictionBooks++;
            }
            // else if (book.getGenre().equals(NON_FICTION)) {
            //     countNonFictionBooks++;
            // }       
        }
        if (((double)countFictionBooks / listPerson2.length) >= PREFERENCE_LEVEL_PERCENTAGE) {
            getStatisticsDataStore().setGenrePreference2(Genre.FICTION);
        } else {
            getStatisticsDataStore().setGenrePreference2(Genre.NON_FICTION);
        }
    }

    private void computePercentageBooks1ComparedTo2() {

        int numerator = getBooksInCommon().size();
        int denominator = getReadingListDataStore().getBookReadingListPerson1().length;

        getStatisticsDataStore().setPercentageBooks1ComparedTo2((double)numerator / denominator);  // TODO: Check integer vs. double division, ensure my syntax is correct.
    }

    private void computePercentageBooks2ComparedTo1() {

        int numerator = getBooksInCommon().size();
        int denominator = getReadingListDataStore().getBookReadingListPerson2().length;

        getStatisticsDataStore().setPercentageBooks2ComparedTo1((double)numerator / denominator);
    }

    public void pickRandomBookInCommon() {
        getStatisticsDataStore().setRandomBookInCommon(new Book("Java Programming", "John Doe", "1234567890", Genre.NON_FICTION));
    }

    public ArrayList<Object> compileInterestStatistics() {  // TODO: ensure this method is needed/is accurate/complete.
        
        ArrayList<Object> result = new ArrayList<>();

        // The order here will be the order in which the information will be displayed.
        result.add(getStatisticsDataStore().getGenrePreference1());
        result.add(getStatisticsDataStore().getGenrePreference2());

        result.add((Double)(getStatisticsDataStore().getPercentageBooks1ComparedTo2()));
        result.add((Double)(getStatisticsDataStore().getPercentageBooks2ComparedTo1()));

        result.add(getStatisticsDataStore().getRandomBookInCommon());

        return result;
    }
}
