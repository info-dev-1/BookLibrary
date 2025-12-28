import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * This class loads reading list details from the database library.db.
 * 
 * Note: "reading list" and "booklist" usually refer to the same thing. However, the latter is more often used in SQL-related code in this class.
 */
public class ReadingListLoader {

    private Connection conn = null;

    private static final int ID_PERSON_1 = 1;  

    private static final int ID_PERSON_2 = 2; 

    private String namePerson1 = null; 

    private String namePerson2 = null; 

    private Book[] readingListPerson1 = null; 

    private Book[] readingListPerson2 = null; 


    public String getNamePerson1() {
        return namePerson1;
    }

    public String getNamePerson2() {
        return namePerson2;
    }
    
    public Book[] getReadingListPerson1() {
        return readingListPerson1;
    }

    public Book[] getReadingListPerson2() {
        return readingListPerson2;
    }

    private void setNamePerson1(String namePerson1) {
        this.namePerson1 = namePerson1;
    }

    private void setNamePerson2(String namePerson2) {
        this.namePerson2 = namePerson2;
    }

    private void setReadingListPerson1(Book[] readingListPerson1) {
        this.readingListPerson1 = readingListPerson1;
    }

    private void setReadingListPerson2(Book[] readingListPerson2) {
        this.readingListPerson2 = readingListPerson2;
    }
    
    // This method's code is adapted from the second module 6 article reading, pp. 3-4.
    private Connection connectToDB() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:library.db");
            return conn;
        }
        catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void loadAllDataForComparisonFeature() {
        setNamePerson1(getNameFromDB(ID_PERSON_1));
        setNamePerson2(getNameFromDB(ID_PERSON_2));

        setReadingListPerson1(getBooklistFromDB(ID_PERSON_1)); 
        setReadingListPerson2(getBooklistFromDB(ID_PERSON_2)); 
    }

    // Code adapted from Module 6 reading, article 2.
    private String getNameFromDB(int personID) {
        conn = connectToDB();
        String name = null;  // Default/starting value.

        // Call the CRUD/READ operation method, WHERE person_id = personID. 
        ResultSet nameResultSet = readNameFromPersonsTable(personID);

        try {
            // process the results 
            nameResultSet.next();
            name = nameResultSet.getString("name");

            // close the connection.
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

	    // return the String name result.
        return name;
    }

    private int getBooklistIDFromDB(int personID) {
        conn = connectToDB();
        Integer booklistIDInteger = null;  // Default/starting value.
        int booklistID = 0;  // Default/starting value.

        ResultSet booklistIDResultSet = readBooklistIDFromBooklistsTable(personID);

        try {
            // Extract the booklist ID from the result set.
            booklistIDResultSet.next();
            booklistIDInteger = Integer.parseInt(booklistIDResultSet.getString("booklist_id"));
            booklistID = booklistIDInteger.intValue();
            
            conn.close();
        }
        catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
        return booklistID;
    }

    private Book[] getBooklistFromDB(int personID) {
        
        int booklistID = getBooklistIDFromDB(personID);

        conn = connectToDB();
        ResultSet booklistResultSet = readValuesFromBooksTable(booklistID);

        // Iterate over that result set, storing the data in the Book instances.
        Book[] booklist = constructBooklistFromResultSet(booklistResultSet);

        try {
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        return booklist;
        
    }

    // Process the ResultSet and construct an array of Books.
    private Book[] constructBooklistFromResultSet(ResultSet resultSet) {
        ArrayList<Book> booklistBuilder = new ArrayList<>();
        GenreFormatConverter formatConverter = new GenreFormatConverter();

        try {
            while (resultSet.next()) {
                // Extract the book details.
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                String genreString = resultSet.getString("genre");
            
                // Convert genre from String to Enum.
                formatConverter.setGenreValueString(genreString);
                formatConverter.convertGenreFormat();
                Genre genreEnum = formatConverter.getGenreValueEnum();

                booklistBuilder.add(new Book(title, author, isbn, genreEnum));
            }
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        // Syntax from Google Gemini, 12/8/25.
        Book[] bookArray = booklistBuilder.stream().toArray(Book[]::new);
        return bookArray;
    }

    // Given a person_id, find and return the ResultSet with an associated name.
    // Code adapted from Module 6 reading, article 2.
    private ResultSet readNameFromPersonsTable(int personID) {
        ResultSet result = null;
        try {
            String readPersonsSql = "SELECT name FROM persons WHERE person_id = " + personID;
            PreparedStatement pstmt = conn.prepareStatement(readPersonsSql);
            result = pstmt.executeQuery();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    // Given a person_id, find and return the ResultSet with an associated booklist_id.
    private ResultSet readBooklistIDFromBooklistsTable(int personID) {
        ResultSet result = null;
        try {
            String readBooklistsSql = "SELECT booklist_id FROM booklists WHERE person_id = " + personID;
            PreparedStatement pstmt = conn.prepareStatement(readBooklistsSql);
            result = pstmt.executeQuery();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    // Given a booklist_id, find and return the ResultSet with associated details for books.
    private ResultSet readValuesFromBooksTable(int booklistID) {
        ResultSet result = null;
        try {
            String readBooksSql = "SELECT title, author, isbn, genre FROM books WHERE booklist_id = " + booklistID;
            PreparedStatement pstmt = conn.prepareStatement(readBooksSql);
            result = pstmt.executeQuery();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

 }
