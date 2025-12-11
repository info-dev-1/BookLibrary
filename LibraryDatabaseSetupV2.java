import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class contains code which sets up library.db for the reading list comparison feature.
 * 
 * @author info-dev-1
 * @since 12/9/25
 */
public class LibraryDatabaseSetupV2 {

    private Connection conn = null;  // For connecting to the SQLite database.

    // Run the setup script/code.
    public static void main(String[] args) {

        LibraryDatabaseSetupV2 setupScriptObject = new LibraryDatabaseSetupV2();

        // setupScriptObject.createTable();  // Completed on 12/9 pm.

        // setupScriptObject.insertInitialData();  // Completed on 12/10.

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

    private void createTable() {
        
        // Necessary order of creation: persons, booklists, books.
        
        // Note: This was run already, 12/9 pm.
        // createPersonsTable();  

        // Note: This was run already, 12/9 pm.
        // createBooklistsTable();
        
        // Note: This was run already, 12/9 pm.
        // createBooksTable();
    }

    // Create the persons table.
    private void createPersonsTable() {
        conn = connectToDB();
        int rowsAffected = 0;

        try {
            String createPersonsSql = """
                CREATE TABLE IF NOT EXISTS persons (
                    person_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL
                )""";

            // Prepare and execute the statement.
            PreparedStatement pstmt = conn.prepareStatement(createPersonsSql);
            rowsAffected = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
    }

    // Create the booklists table.
    private void createBooklistsTable() {
        conn = connectToDB();
        int rowsAffected = 0;

        try {
            String createBooklistsSql = """
                CREATE TABLE IF NOT EXISTS booklists (
                    booklist_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    person_id INTEGER,
                    FOREIGN KEY(person_id) REFERENCES persons(person_id)
                )""";

            // Prepare and execute the statement.
            PreparedStatement pstmt = conn.prepareStatement(createBooklistsSql);
            rowsAffected = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
    }

    // Create the books table.
    private void createBooksTable() {
        conn = connectToDB();
        int rowsAffected = 0;

        try {
            String createBooksSql = """
                CREATE TABLE IF NOT EXISTS books (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT,
                    author TEXT,
                    isbn TEXT UNIQUE,
                    genre TEXT,
                    available INTEGER DEFAULT 1,  -- 1 for available, 0 for borrowed
                    booklist_id INTEGER,
                    FOREIGN KEY(booklist_id) REFERENCES booklists(booklist_id)
                )""";

            // Prepare and execute the statement.
            PreparedStatement pstmt = conn.prepareStatement(createBooksSql);
            rowsAffected = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
    }

    private void insertInitialData() {

        // Necessary order of updating: persons, booklists, books.

        // updatePersonsTable();  // This was run 12/10.

        // updateBooklistsTable();  // This was run 12/10.

        // updateBooksTable();  // This was run 12/10.
    }

    // Populate the persons table.
    private void updatePersonsTable() {
        conn = connectToDB();
        int rowsAffected = 0;

        try {
            String updatePersonsSql = """
                INSERT OR IGNORE INTO persons (name) VALUES ( ? )
                """;
            PreparedStatement pstmt = conn.prepareStatement(updatePersonsSql);

            pstmt.setString(1, "Alice");
            rowsAffected += pstmt.executeUpdate();

            pstmt.setString(1, "Bob");
            rowsAffected += pstmt.executeUpdate();

            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);   
    }

    // Populate the booklists table.
    private void updateBooklistsTable() {
        conn = connectToDB();
        int rowsAffected = 0;

        try {
            String updateBooklistsSql = """
                INSERT OR IGNORE INTO booklists (person_id) VALUES ( ? )
                """;
            PreparedStatement pstmt = conn.prepareStatement(updateBooklistsSql);

            pstmt.setInt(1, 1);
            rowsAffected += pstmt.executeUpdate();

            pstmt.setInt(1, 2);
            rowsAffected += pstmt.executeUpdate();

            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
    }

    // Populate the books table.
    private void updateBooksTable() {
        conn = connectToDB();
        int rowsAffected = 0;

        try {
            // only the last 2 parameters are INTEGER. All other ones are TEXT.
            String updateBooksSql = """
                INSERT INTO books (title, author, isbn, genre, available, booklist_id) VALUES (?, ?, ?, ?, ?, ?)
                """;
            PreparedStatement pstmt = conn.prepareStatement(updateBooksSql);

            // Commented-out b/c these data were successfully inserted into the DB.

            // Books only on the list of the person identified by person_id 1
            // pstmt.setString(1, "The Lord of the Rings");
            // pstmt.setString(2, "J.R.R. Tolkien");
            // pstmt.setString(3, "47841");
            // pstmt.setString(4, "Fiction");
            // pstmt.setInt(5, 1);
            // pstmt.setInt(6, 1);
            // rowsAffected += pstmt.executeUpdate();

            // pstmt.setString(1, "Harry Potter and the Sorcerer's Stone");
            // pstmt.setString(2, "J.K. Rowling");
            // pstmt.setString(3, "72050");
            // pstmt.setString(4, "Fiction");
            // pstmt.setInt(5, 1);
            // pstmt.setInt(6, 1);
            // rowsAffected += pstmt.executeUpdate();

            // pstmt.setString(1, "The Martian");
            // pstmt.setString(2, "Andy Weir");
            // pstmt.setString(3, "05013");
            // pstmt.setString(4, "Fiction");
            // pstmt.setInt(5, 1);
            // pstmt.setInt(6, 1);
            // rowsAffected += pstmt.executeUpdate();

            // Books on both persons' lists. (So, they are entered twice, 1 for each booklist_id.)
            // pstmt.setString(1, "The Hitchhiker's Guide to the Galaxy");
            // pstmt.setString(2, "Douglas Adams");
            // pstmt.setString(3, "66302");
            // pstmt.setString(4, "Fiction");
            // pstmt.setInt(5, 1);
            // pstmt.setInt(6, 1);
            // rowsAffected += pstmt.executeUpdate();

            pstmt.setString(1, "The Hitchhiker's Guide to the Galaxy");
            pstmt.setString(2, "Douglas Adams");
            pstmt.setString(3, "66302");
            pstmt.setString(4, "Fiction");
            pstmt.setInt(5, 1);
            pstmt.setInt(6, 2);
            rowsAffected += pstmt.executeUpdate();

            // pstmt.setString(1, "Git for Programmers");
            // pstmt.setString(2, "Jesse Liberty");
            // pstmt.setString(3, "35772");
            // pstmt.setString(4, "Nonfiction");
            // pstmt.setInt(5, 1);
            // pstmt.setInt(6, 1);
            // rowsAffected += pstmt.executeUpdate();

            pstmt.setString(1, "Git for Programmers");
            pstmt.setString(2, "Jesse Liberty");
            pstmt.setString(3, "35772");
            pstmt.setString(4, "Noniction");
            pstmt.setInt(5, 1);
            pstmt.setInt(6, 2);
            rowsAffected += pstmt.executeUpdate();

            // Books only on the list of the person identified by person_id 2
            // pstmt.setString(1, "Good Code, Bad Code");
            // pstmt.setString(2, "Tom Long");
            // pstmt.setString(3, "33198");
            // pstmt.setString(4, "Noniction");
            // pstmt.setInt(5, 1);
            // pstmt.setInt(6, 2);
            // rowsAffected += pstmt.executeUpdate();

            // pstmt.setString(1, "Statistics");
            // pstmt.setString(2, "James McClave, Terry Sincich");
            // pstmt.setString(3, "19453");
            // pstmt.setString(4, "Noniction");
            // pstmt.setInt(5, 1);
            // pstmt.setInt(6, 2);
            // rowsAffected += pstmt.executeUpdate();

            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
    }

}
