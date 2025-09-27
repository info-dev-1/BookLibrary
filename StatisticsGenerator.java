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

    private ArrayList<Book> booksInCommon = null;  // Books which are common to both people's reading lists.

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



    public StatisticsGenerator(ReadingListDataStore dataStore, ArrayList<Book> booksInCommon) {
        readingListDataStore = dataStore;
        this.booksInCommon = booksInCommon;
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
            setGenrePreference1(Genre.FICTION);
        } else {
            setGenrePreference1(Genre.NON_FICTION);
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
            setGenrePreference2(Genre.FICTION);
        } else {
            setGenrePreference2(Genre.NON_FICTION);
        }
    }

    private void computePercentageBooks1ComparedTo2() {

        int numerator = getBooksInCommon().size();
        int denominator = getReadingListDataStore().getBookReadingListPerson1().length;

        setPercentageBooks1ComparedTo2((double)numerator / denominator);  // TODO: Check integer vs. double division, ensure my syntax is correct.
    }

    private void computePercentageBooks2ComparedTo1() {

        int numerator = getBooksInCommon().size();
        int denominator = getReadingListDataStore().getBookReadingListPerson2().length;

        setPercentageBooks2ComparedTo1((double)numerator / denominator);
    }

    public void pickRandomBookInCommon() {
        setRandomBookInCommon(new Book("Java Programming", "John Doe", "1234567890", Genre.NON_FICTION));
    }

    public ArrayList<Object> getInterestResults() {
        
        ArrayList<Object> result = new ArrayList<>();

        // The order here will be the order in which the information will be displayed.
        result.add(getGenrePreference1());
        result.add(getGenrePreference2());

        result.add((Double)(getPercentageBooks1ComparedTo2()));
        result.add((Double)(getPercentageBooks2ComparedTo1()));

        result.add(getRandomBookInCommon());

        return result;
    }
}
