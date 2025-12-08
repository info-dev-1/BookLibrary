import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReadingListLoader {

    private Connection conn = null;

    private static final int ID_PERSON_1 = 0;  

    private static final int ID_PERSON_2 = 1; 

    private String namePerson1 = null; 

    private String namePerson2 = null; 

    private Book[] readingListPerson1 = null; 

    private Book[] readingListPerson2 = null; 


    private String getNamePerson1() {
        return namePerson1;
    }

    private String getNamePerson2() {
        return namePerson2;
    }
    
    private Book[] getReadingListPerson1() {
        return readingListPerson1;
    }

    private Book[] getReadingListPerson2() {
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
    

    // This method's code is adapted from the second module 9 article reading, pp. 3-4.
    private Connection connectToDB() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:test_db_presence.db");  // TODO: change the db file to the actual db filename.
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
        setReadingListPerson1(getReadingListFromDB(ID_PERSON_1)); 
        setReadingListPerson2(getReadingListFromDB(ID_PERSON_2)); 
    }

    private String getNameFromDB(int personID) {
        conn = connectToDB();

        // Call the CRUD/READ operation method, WHERE person_id = personID. 

 

	    // process the results 

 

	    // close the connection 

	    // return the String name result. 
    }

    private Book[] getReadingListFromDB(int personID) {
        
        // TODO: decompose the following into methods:
        // call a READ operation method: SELECT book_list_id FROM book_lists WHERE person_id = personID.
        // process the results, storing an int, targetBookListID.

        // call getReadingListResultSetFromDB(targetBookListID)

        // iterate over that result set, storing the data in the Book instances.

        // return the result.
        


    }

    private ResultSet getReadingListResultSetFromDB(int booklistID) {
        // SELECT title, author, isbn, genre FROM books WHERE booklist_id = booklistID
        // 
    }
 }
