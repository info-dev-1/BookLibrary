/**
 * This class contains code which tests the GenreFormatConverter's functionality.
 * 
 * @author info-dev-1
 * @since 12/1/25
 */
public class GenreFormatConverterTest {

    // Substitute/stand-in database values.

    private static final String NON_FICTION_GENRE_VALUE_STRING = "Nonfiction";

    private static final String FICTION_GENRE_VALUE_STRING = "Fiction";

    private static final String SPECIFIC_FICTION_GENRE_VALUE_STRING = "Fantasy";

    private static final String INVALID_GENRE_VALUE_STRING = "not valid";

    // Run the tests.
    public static void main(String[] args) {
        
        GenreFormatConverterTest.displayTestingIntro();
        GenreFormatConverterTest tester = new GenreFormatConverterTest();
        
        tester.testNonfictionConversion();
        tester.testLabelFictionConversion();
        tester.testSpecificFictionConversion();
        tester.testInvalidConversion();
    }

    private static void displayTestingIntro() {
        System.out.println("Testing the GenreFormatConverter class functionality");
        System.out.println("---------------------------------------------------------\n");
    }

    // Test the conversion of a nonfiction genre value.
    private void testNonfictionConversion() {
        String nonfiction = GenreFormatConverterTest.NON_FICTION_GENRE_VALUE_STRING;
        GenreFormatConverter converterNonfiction = new GenreFormatConverter(nonfiction);

        converterNonfiction.convertGenreFormat();

        Genre result = converterNonfiction.getGenreValueEnum();
        System.out.println("Testing String-to-Enum conversion.");
        System.out.println("The String is: " + nonfiction);
        System.out.println("Genre should be " + Genre.NON_FICTION + ": " + result.toString());
        System.out.println();
    }

    // Test the conversion of a fiction genre value.
    private void testLabelFictionConversion() {
        String fiction = GenreFormatConverterTest.FICTION_GENRE_VALUE_STRING;
        GenreFormatConverter converterFiction = new GenreFormatConverter(fiction);

        converterFiction.convertGenreFormat();

        Genre result = converterFiction.getGenreValueEnum();
        System.out.println("Testing String-to-Enum conversion.");
        System.out.println("The String is: " + fiction);
        System.out.println("Genre should be " + Genre.FICTION + ": " + result.toString());
        System.out.println();
    }

    // Test the conversion of a specific, fiction genre value (e.g., "Fantasy").
    private void testSpecificFictionConversion() {
        String specificFiction = GenreFormatConverterTest.SPECIFIC_FICTION_GENRE_VALUE_STRING;
        GenreFormatConverter converterSpecificFiction = new GenreFormatConverter(specificFiction);

        converterSpecificFiction.convertGenreFormat();

        Genre result = converterSpecificFiction.getGenreValueEnum();
        System.out.println("Testing String-to-Enum conversion.");
        System.out.println("The String is: " + specificFiction);
        System.out.println("Genre should be " + Genre.FICTION + ": " + result.toString());
        System.out.println();
    }

    // Test the conversion when an invalid genre value is provided.
    private void testInvalidConversion() {
        String invalidGenre = GenreFormatConverterTest.INVALID_GENRE_VALUE_STRING;
        GenreFormatConverter converterUsingInvalidGenre = new GenreFormatConverter(invalidGenre);

        converterUsingInvalidGenre.convertGenreFormat();

        Genre result = converterUsingInvalidGenre.getGenreValueEnum();
        System.out.println("Testing String-to-Enum conversion of an invalid genre.");
        System.out.println("The invalid genre (as a String) is: " + invalidGenre);
        System.out.println("Genre should be " + Genre.INVALID_GENRE + ": " + result.toString());
        System.out.println();
    }

}
