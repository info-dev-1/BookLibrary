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
    
    private static final double PREFERENCE_LEVEL = 0.6;  // Minimum fraction of books of a genre which implies a "preference" for that genre.

    private ReadingListDataStore readingListDataStore;  // Holds the reading lists for both people.

    private ArrayList<Book> booksInCommon = null;  // Books which are common to both people's reading lists.

    private StatisticsDataStore statisticsDataStore = new StatisticsDataStore();  // For storing statistics.

    private boolean zeroBooksInCommonFlag = false;


    public StatisticsGenerator(ReadingListDataStore listDataStore) {
        this.readingListDataStore = listDataStore;
    }


    // Getters and setters

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

    private void setZeroBooksInCommonFlag(boolean flagValue) {
        zeroBooksInCommonFlag = flagValue;
    }

    private boolean getZeroBooksInCommonFlag() {
        return zeroBooksInCommonFlag;
    }

    // Compute interests and shared interests regarding the reading lists. See the contained methods' definitions for details.
    public void computeInterestStatistics() {

        computeGenrePreferencePerson1();
        computeGenrePreferencePerson2();
        
        computePercentageBooks1ComparedTo2();
        computePercentageBooks2ComparedTo1();

        if (getBooksInCommon().size() == 0) {
            setZeroBooksInCommonFlag(true);
        }
        else {
            pickRandomBookInCommon();
        }
        
    }

    // Compute and store a GenrePreference enum-value which indicates person 1's genre "preference".
    // Require that bookReadingListPerson1 is not empty. TODO: If it is - how to deal with that exception?
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

    // Compute and store a GenrePreference enum-value which indicates person 2's genre "preference".
    // Require that bookReadingListPerson2 is not empty. TODO: If it is - how to deal with that exception?
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

    // Given a reading list, count the total of fiction books and non-fiction books.
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
        // Return an array of size 2: index 0 accesses the count for ficiton books, index 1 the count for non-fiction books.
        return new int[] { countFictionBooks, countNonFictionBooks };
    }

    // Determine whether the the given person's reading list indicates a "preference" for the fiction genre.
    private boolean personPrefersFiction(int lengthReadingList, int countFictionBooks) {
        return ((double)countFictionBooks / lengthReadingList) >= PREFERENCE_LEVEL;
    }

    // Determine whether the the given person's reading list indicates a "preference" for the non-fiction genre.
    private boolean personPrefersNonFiction(int lengthReadingList, int countNonFictionBooks) {
        return ((double)countNonFictionBooks / lengthReadingList) >= PREFERENCE_LEVEL;
    }

    // Compute the percentage of books on person 1's list which are also on person 2's list.
    // Require that getBooksInCommon() doesn't return null.  // TODO: If it is - how to deal with that exception?
    private void computePercentageBooks1ComparedTo2() {

        int numerator = getBooksInCommon().size();
        int denominator = getReadingListDataStore().getBookReadingListPerson1().length;
        int result = computePercentage(numerator, denominator);

        getStatisticsDataStore().setPercentageBooks1ComparedTo2(result);
    }

    // Compute the percentage of books on person 2's list which are also on person 1's list.
    // Require that getBooksInCommon() doesn't return null.  // TODO: If it is - how to deal with that exception?
    private void computePercentageBooks2ComparedTo1() {

        int numerator = getBooksInCommon().size();
        int denominator = getReadingListDataStore().getBookReadingListPerson2().length;
        int result = computePercentage(numerator, denominator);
        
        getStatisticsDataStore().setPercentageBooks2ComparedTo1(result);
    }

    // Compute an int which is a percentage.
    private int computePercentage(int numerator, int denominator) {

        double resultFraction = (double)numerator / denominator;
        double resultDouble = resultFraction * 100;
        BigDecimal resultBigDecimal = new BigDecimal(resultDouble);
        BigDecimal resultRounded = resultBigDecimal.round(new MathContext(2));

        return resultRounded.intValue();
    }

    // Pick a random book from booksInCommon. Store the result.
    public void pickRandomBookInCommon() {

        Random random = new Random();
        int bound = getBooksInCommon().size();
        int randomIndx = random.nextInt(bound);  // Random index selection.

        Book randomBookInCommon = getBooksInCommon().get(randomIndx);
        getStatisticsDataStore().setRandomBookInCommon(randomBookInCommon);
    }

    // Pack the five interest/shared interest statistics into an ArrayList. Also include the boolean flag, zeroBooksInCommon.
    public ArrayList<Object> compileInterestStatistics() {
        
        ArrayList<Object> result = new ArrayList<>();
        
        result.add(getStatisticsDataStore().getGenrePreferencePerson1());
        result.add(getStatisticsDataStore().getGenrePreferencePerson2());

        result.add((Integer)(getStatisticsDataStore().getPercentageBooks1ComparedTo2()));
        result.add((Integer)(getStatisticsDataStore().getPercentageBooks2ComparedTo1()));

        result.add(Boolean.valueOf(getZeroBooksInCommonFlag()));
        
        if (!getZeroBooksInCommonFlag()) {  // If there are greater than 0 books in common to both people's lists.
            result.add(getStatisticsDataStore().getRandomBookInCommon());
        }
        
        return result;
    }
}
