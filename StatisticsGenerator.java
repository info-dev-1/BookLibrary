import java.util.ArrayList;
import java.util.Random;
import java.math.BigDecimal;
import java.math.MathContext;


/**
 * This class contains code which generates statistics. The statistics relate to interests/shared interests between the two people whose lists we are comparing.
 * 
 * @author info-dev-1
 * @since 9/26/2025
 */

public class StatisticsGenerator {
    
    private static final double PREFERENCE_LEVEL = 0.6;

    private ReadingListDataStore readingListDataStore;

    private ArrayList<Book> booksInCommon = null;  // Books which are common to both people's reading lists.

    private StatisticsDataStore statisticsDataStore = new StatisticsDataStore();

    public StatisticsGenerator(ReadingListDataStore listDataStore) {
        this.readingListDataStore = listDataStore;
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
        computeGenrePreferencePerson1();
        computeGenrePreferencePerson2();
        
        computePercentageBooks1ComparedTo2();
        computePercentageBooks2ComparedTo1();

        pickRandomBookInCommon();
    }


    // Requires that bookReadingListPerson1 is not empty. TODO: If it is - how to deal with that exception?
    private void computeGenrePreferencePerson1() {
        
        Book[] listPerson1 = getReadingListDataStore().getBookReadingListPerson1();

        int[] countBooksBothGenres = countBooksBothGenres(listPerson1);
        int countFictionBooks = countBooksBothGenres[0];
        int countNonFictionBooks = countBooksBothGenres[1];

        if (personPrefersFiction(listPerson1.length, countFictionBooks)) {
            getStatisticsDataStore().setGenrePreferencePerson1(GenrePreference.FICTION);
        }
        else if (personPrefersNonFiction(listPerson1.length, countNonFictionBooks)) {
            getStatisticsDataStore().setGenrePreferencePerson1(GenrePreference.NON_FICTION);
        }
        else {
            getStatisticsDataStore().setGenrePreferencePerson1(GenrePreference.NO_GENRE);
        }
    }

    // Requires that bookReadingListPerson2 is not empty. TODO: If it is - how to deal with that exception?
    private void computeGenrePreferencePerson2() {

        Book[] listPerson2 = getReadingListDataStore().getBookReadingListPerson2();

        int[] countBooksBothGenres = countBooksBothGenres(listPerson2);
        int countFictionBooks = countBooksBothGenres[0];
        int countNonFictionBooks = countBooksBothGenres[1];

        if (personPrefersFiction(listPerson2.length, countFictionBooks)) {
            getStatisticsDataStore().setGenrePreferencePerson2(GenrePreference.FICTION);
        }
        else if (personPrefersNonFiction(listPerson2.length, countNonFictionBooks)) {
            getStatisticsDataStore().setGenrePreferencePerson2(GenrePreference.NON_FICTION);
        }
        else {
            getStatisticsDataStore().setGenrePreferencePerson2(GenrePreference.NO_GENRE);
        }
    }

    // Returns an array of size 2: index 0 accesses the count for ficiton books, index 1 the count for nonfiction books.
    private int[] countBooksBothGenres(Book[] readingList) {
        int countFictionBooks = 0;
        int countNonFictionBooks = 0;

        for (Book book : readingList) {
            if (book.getGenre().equals(Genre.FICTION)) {  
                countFictionBooks++;
            }
            else if (book.getGenre().equals(Genre.NON_FICTION)) {
                countNonFictionBooks++;
            }       
        }
        return new int[] { countFictionBooks, countNonFictionBooks };
    }

    private boolean personPrefersFiction(int lengthReadingList, int countFictionBooks) {
        return ((double)countFictionBooks / lengthReadingList) >= PREFERENCE_LEVEL;
    }

    private boolean personPrefersNonFiction(int lengthReadingList, int countNonFictionBooks) {
        return ((double)countNonFictionBooks / lengthReadingList) >= PREFERENCE_LEVEL;
    }


    // Requires that getBooksInCommon() doesn't return null
    private void computePercentageBooks1ComparedTo2() {

        int numerator = getBooksInCommon().size();
        int denominator = getReadingListDataStore().getBookReadingListPerson1().length;

        int result = computePercentage(numerator, denominator);

        // set the result to the field within the data store
        getStatisticsDataStore().setPercentageBooks1ComparedTo2(result);
    }

    private void computePercentageBooks2ComparedTo1() {

        int numerator = getBooksInCommon().size();
        int denominator = getReadingListDataStore().getBookReadingListPerson2().length;

        int result = computePercentage(numerator, denominator);
        
        getStatisticsDataStore().setPercentageBooks2ComparedTo1(result);
    }

    private int computePercentage(int numerator, int denominator) {

        double fraction = (double)numerator / denominator;
        double resultDouble = fraction * 100;
        BigDecimal resultBigDecimal = new BigDecimal(resultDouble);
        BigDecimal resultRounded = resultBigDecimal.round(new MathContext(2));

        return resultRounded.intValue();
    }

    public void pickRandomBookInCommon() {

        Random random = new Random();
        int bound = getBooksInCommon().size();
        int randomIndx = random.nextInt(bound);

        Book randomBookInCommon = getBooksInCommon().get(randomIndx);
        getStatisticsDataStore().setRandomBookInCommon(randomBookInCommon);
    }

    public ArrayList<Object> compileInterestStatistics() {
        
        ArrayList<Object> result = new ArrayList<>();

        // The order here will be the order in which the information will be displayed.
        result.add(getStatisticsDataStore().getGenrePreferencePerson1());
        result.add(getStatisticsDataStore().getGenrePreferencePerson2());

        result.add((Integer)(getStatisticsDataStore().getPercentageBooks1ComparedTo2()));
        result.add((Integer)(getStatisticsDataStore().getPercentageBooks2ComparedTo1()));

        result.add(getStatisticsDataStore().getRandomBookInCommon());

        return result;
    }
}
