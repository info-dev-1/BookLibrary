import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;  // TODO: research this to make sure I don't need it. I thought I needed it according to a Gemini syntax excerpt in constructBooklistFromResultSet.

/**
 * Note: "reading list" and "booklist" usually refer to the same thing. However, the latter is more often used in SQL-related code in this class.
 * 
 */
public class ReadingListLoader {

    private Connection conn = null;

    // TODO: Note the documentation of the configuration file which contains these ID values. I made some documentation and I need to find it.
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
    
    // TODO: fix the module number, I don't think it is 9.
    // This method's code is adapted from the second module 9 article reading, pp. 3-4.
    private Connection connectToDB() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:library.db");  // TODO: change the db file to the actual db filename.
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
        
        // TODO: decompose the following into methods:
        // call a READ operation method: SELECT booklist_id FROM book_lists WHERE person_id = personID.
        // process the results, storing an int, targetBookListID.
        int booklistID = getBooklistIDFromDB(personID);
           
        // NOTE: The following two lines are not necessary, because it would be easier to keep track of a more monolithic method (getBooklistFromDB).
        // // call getReadingListResultSetFromDB(targetBookListID)
        // ResultSet booklistResultSet = getBooklistResultSetFromDB(booklistID);

        conn = connectToDB();
        ResultSet booklistResultSet = readValuesFromBooksTable(booklistID);

        try {
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        // iterate over that result set, storing the data in the Book instances.
        Book[] booklist = constructBooklistFromResultSet(booklistResultSet);
        return booklist;
    }

    private Book[] constructBooklistFromResultSet(ResultSet resultSet) {
        ArrayList<Book> booklistBuilder = new ArrayList<>();
        GenreFormatConverter formatConverter = new GenreFormatConverter();

        try {
            resultSet.next();
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String isbn = resultSet.getString("isbn");
            String genreString = resultSet.getString("genre");
        
            formatConverter.setGenreValueString(genreString);
            formatConverter.convertGenreFormat();
            Genre genreEnum = formatConverter.getGenreValueEnum();

            booklistBuilder.add(new Book(title, author, isbn, genreEnum));
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        // Syntax from Gemini, 12/8/25
        Book[] bookArray = booklistBuilder.stream().toArray(Book[]::new);
        return bookArray;
    }

    

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

    // Given a personID, read the booklist_id.
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

    // TODO: NOTE: This method assumes the books table has a column booklist_id, which it probably doesn't as of 12/8/25.
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


    // 12/9 process: 1) create a new test table. 2) insert some values into it.
    public void testingCreateFruitsTableInDB() {

        conn = connectToDB();
        // String name = null;
        int rowsAffected = 0;

        try {
            String createFruitsSql = """
                CREATE TABLE IF NOT EXISTS fruits (
                    fruit_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    fruit_name TEXT,
                    fruit_color TEXT,
                    quantity INTEGER DEFAULT 1
                )""";

            PreparedStatement pstmt = conn.prepareStatement(createFruitsSql);
            rowsAffected = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
        // System.out.println("Test: this string was successfully read from the database: " + name);

    }

    public void testingUpdateFruitsTable() {
        conn = connectToDB();
        int rowsAffected = 0;

        try {
            String updateFruitsSql = """
                INSERT OR IGNORE INTO fruits
                (fruit_name, fruit_color, quantity)
                VALUES ( "Red Apple", "Red", 5 )
                """;

            PreparedStatement pstmt = conn.prepareStatement(updateFruitsSql);
            rowsAffected = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);

    }
 }
