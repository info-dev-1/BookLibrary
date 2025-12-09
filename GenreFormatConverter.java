/**
 * This class enables the conversion of text-based (String, in Java) Genres to the Enum format.
 * 
 * @author info-dev-1
 * @since 12/1/2025
 */
public class GenreFormatConverter {
    
    private String genreValueString;  // The value of the genre, in String format.

    private Genre genreValueEnum;  // The value of the genre, in Enum format.

    // The text representation of the fiction genre.
    // This should match any occurrences of the fiction Genre value in the database.
    private static final String FICTION_GENRE_LABEL = "Fiction";

    // The text representation of the Nonfiction genre.
    // This should match any occurrences of the nonfiction Genre value in the database.
    private static final String NON_FICTION_GENRE_LABEL = "Nonfiction";

    // This can be updated as we encounter additional, more specific genres (such as Mystery).
    private static final String[] SPECIFIC_FICTION_GENRES = { "Fantasy", "Romance", "Thriller" };

    // As of 12/1, we don't have any specific nonfiction genres (such as "History").
    // Only nonfiction book records with the "Nonfiction" genre will be added to the database.

    
    // Getters and setters.

    private String getGenreValueString() {
        return this.genreValueString;
    }

    public void setGenreValueString(String genreValueString) {
        this.genreValueString = genreValueString;
    }

    public Genre getGenreValueEnum() {
        return this.genreValueEnum;
    }

    private void setGenreValueEnum(Genre value) {
        this.genreValueEnum = value;
    }

    // Convert the genre from String to Enum, and store that result in the instance field.
    public void convertGenreFormat() {
        String dataToConvert = getGenreValueString();
        Genre conversionResult = null;

        if (isFiction(dataToConvert)) {
            conversionResult = Genre.FICTION;
        }
        else if (isNonFiction(dataToConvert)) {
            conversionResult = Genre.NON_FICTION;
        }
        
        // TODO: Proper error-handling for when the genre of a book (as it is stored in the database) is not one of the supported variants.
        // As of 12/1/25, info-dev-1 is coding this to store an "invalid genre" value.
        else {
            conversionResult = Genre.INVALID_GENRE;
        }
        setGenreValueEnum(conversionResult);
    }

    private boolean isFiction(String genreString) {
        return genreString.equals(FICTION_GENRE_LABEL)
            || isMemberOfSpecificFictionGenres(genreString);
    }

    private boolean isMemberOfSpecificFictionGenres(String genreString) {
        for (String stringToCheck : SPECIFIC_FICTION_GENRES) {
            if (genreString.equals(stringToCheck)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNonFiction(String genreString) {
        return genreString.equals(NON_FICTION_GENRE_LABEL);
    }

    // Note: isMemberOfSpecificNonFictionGenres(String genreString) could be added if there were specific, nonfiction genres, such as "History".

}
