import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibraryDatabaseSetupV2 {

    private Connection conn = null;  // For connecting to the SQLite database.

    // Run the setup script/code.
    public static void main(String[] args) {

        LibraryDatabaseSetupV2 setupScriptObject = new LibraryDatabaseSetupV2();
        setupScriptObject.createTable();

        // setupScriptObject.insertAllInitialData();

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

    // TODO: confirm table names with the mod9 design document.
    private void createTable() {
         
        // Note: Will kept librarians and patrons tables as-is, because they were created correctly/without error.
        
        // necessary order of creation: persons, booklists, books, checkouts, fines, holds.
        
        // Note: This was run already, 12/9 pm.
        // createPersonsTable();  

        // Note: This was run already, 12/9 pm.
        // createBooklistsTable();
        

        createBooksTable();  
        // createCheckoutsTable();  

        // createFinesTable();

        // createHoldsTable();

        
        
    }

    private void createPersonsTable() {
        conn = connectToDB();
        int rowsAffected = 0;

        try {
            String createPersonsSql = """
                CREATE TABLE IF NOT EXISTS persons (
                    person_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL
                )""";

            PreparedStatement pstmt = conn.prepareStatement(createPersonsSql);
            rowsAffected = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
    }

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

            PreparedStatement pstmt = conn.prepareStatement(createBooklistsSql);
            rowsAffected = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
    }


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

            PreparedStatement pstmt = conn.prepareStatement(createBooksSql);
            rowsAffected = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
    }

    private void updateBooksTableAddBooklistIDColumn() {
        conn = connectToDB();
        int rowsAffected = 0;

        try {
            String updateBooksSql = """
                ALTER TABLE books ADD COLUMN booklist_id INTEGER
                """;

            PreparedStatement pstmt = conn.prepareStatement(updateBooksSql);
            rowsAffected = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
    }

    private void createCheckoutsTable() {
        conn = connectToDB();
        int rowsAffected = 0;

        try {
            String createCheckoutsSql = """
                CREATE TABLE IF NOT EXISTS checkouts (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    patron_id INTEGER,
                    book_id INTEGER,
                    checkout_date TEXT,
                    due_date TEXT,
                    return_date TEXT,
                    FOREIGN KEY(patron_id) REFERENCES patrons(id),
                    FOREIGN KEY(book_id) REFERENCES books(id)
                )""";

            PreparedStatement pstmt = conn.prepareStatement(createCheckoutsSql);
            rowsAffected = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
    }

    private void createFinesTable() {
        conn = connectToDB();
        int rowsAffected = 0;

        try {
            String createFinesSql = """
                CREATE TABLE IF NOT EXISTS fines (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    patron_id INTEGER,
                    checkout_id INTEGER,
                    amount REAL,
                    paid INTEGER DEFAULT 0,  -- 0 for unpaid, 1 for paid
                    FOREIGN KEY(patron_id) REFERENCES patrons(id),
                    FOREIGN KEY(checkout_id) REFERENCES checkouts(id)
                )""";

            PreparedStatement pstmt = conn.prepareStatement(createFinesSql);
            rowsAffected = pstmt.executeUpdate();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("Rows affected: " + rowsAffected);
    }

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
