import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.text.MessageFormat;

public class LibraryDatabaseSetup {
    public static void main(String[] args) {
        // Connect to (or create) the SQLite database file


        String url = "jdbc:sqlite:library.db"; // Or however you define it

        // Add this line immediately before the connection attempt
        System.out.println("DEBUG: Attempting to connect to: " + url);
        System.out.println("DEBUG: Current Working Directory: " + System.getProperty("user.dir"));


        try (Connection connection = DriverManager.getConnection(url)) {
            // Create tables if they don't exist
            String createTablesSql = """
                    CREATE TABLE IF NOT EXISTS librarians (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        username TEXT UNIQUE,
                        hashed_password TEXT
                    );

                    CREATE TABLE IF NOT EXISTS patrons (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT,
                        email TEXT,
                        phone TEXT
                    );

                    CREATE TABLE IF NOT EXISTS books (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        title TEXT,
                        author TEXT,
                        isbn TEXT UNIQUE,
                        genre TEXT,
                        available INTEGER DEFAULT 1  -- 1 for available, 0 for borrowed
                    );

                    CREATE TABLE IF NOT EXISTS checkouts (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        patron_id INTEGER,
                        book_id INTEGER,
                        checkout_date TEXT,
                        due_date TEXT,
                        return_date TEXT,
                        FOREIGN KEY(patron_id) REFERENCES patrons(id),
                        FOREIGN KEY(book_id) REFERENCES books(id)
                    );

                    CREATE TABLE IF NOT EXISTS fines (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        patron_id INTEGER,
                        checkout_id INTEGER,
                        amount REAL,
                        paid INTEGER DEFAULT 0,  -- 0 for unpaid, 1 for paid
                        FOREIGN KEY(patron_id) REFERENCES patrons(id),
                        FOREIGN KEY(checkout_id) REFERENCES checkouts(id)
                    );

                    CREATE TABLE IF NOT EXISTS holds (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        patron_id INTEGER,
                        book_id INTEGER,
                        request_date TEXT,
                        status TEXT DEFAULT 'pending',  -- e.g., 'pending', 'ready', 'fulfilled'
                        FOREIGN KEY(patron_id) REFERENCES patrons(id),
                        FOREIGN KEY(book_id) REFERENCES books(id)
                    );""";
        

            try (PreparedStatement stmt = connection.prepareStatement(createTablesSql)) {
                stmt.executeUpdate();
            }

            // Insert minimal data for librarians (for authentication feature)
            String insertLibrarianSql = "INSERT OR IGNORE INTO librarians (username, hashed_password) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertLibrarianSql)) {
                stmt.setString(1, "admin");
                stmt.setString(2, hashPassword("librarian123"));
                stmt.executeUpdate();

                stmt.setString(1, "librarian1");
                stmt.setString(2, hashPassword("pass456"));
                stmt.executeUpdate();
            }

            // Insert minimal data for patrons
            String insertPatronSql = "INSERT OR IGNORE INTO patrons (id, name, email, phone) VALUES (?, ?, ?, ?)";
            // String insertPatronSql = "INSERT OR IGNORE INTO patrons (name, email, phone) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertPatronSql)) {
                stmt.setInt(1, 1);
                stmt.setString(2, "John Doe Test");
                stmt.setString(3, "john@example.com");
                stmt.setString(4, "123-456-7890");
                stmt.executeUpdate();

                stmt.setInt(1, 2);
                stmt.setString(2, "Jane Smith");
                stmt.setString(3, "jane@example.com");
                stmt.setString(4, "987-654-3210");
                stmt.executeUpdate();

                stmt.setInt(1, 3);
                stmt.setString(2, "Will Bush");
                stmt.setString(3, "will@example.com");
                stmt.setString(4, "999-888-7777");
                stmt.executeUpdate();

                stmt.setInt(1, 4);
                stmt.setString(2, "Dianna Bova");
                stmt.setString(3, "dianna@example.com");
                stmt.setString(4, "111-222-3333");
                stmt.executeUpdate();
            }

            // Insert minimal data for books (updated to match provided minimal data, with genre added)
            String insertBookSql = "INSERT OR IGNORE INTO books (id, title, author, isbn, genre, available) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertBookSql)) {
                stmt.setInt(1, 1);
                stmt.setString(2, "The Lord of the Rings");
                stmt.setString(3, "J. R. R. Tolkien");
                stmt.setString(4, "9780743273565");
                stmt.setString(5, "Fantasy");
                stmt.setInt(6, 1);
                stmt.executeUpdate();

                stmt.setInt(1, 2);
                stmt.setString(2, "To Kill a Mockingbird");
                stmt.setString(3, "Harper Lee");
                stmt.setString(4, "9780061120084");
                stmt.setString(5, "Thriller");
                stmt.setInt(6, 1);
                stmt.executeUpdate();

                stmt.setInt(1, 3);
                stmt.setString(2, "1984");
                stmt.setString(3, "George Orwell");
                stmt.setString(4, "9780451524935");
                stmt.setString(5, "Fiction");
                stmt.setInt(6, 1);
                stmt.executeUpdate();

                stmt.setInt(1, 4);
                stmt.setString(2, "The Notebook");
                stmt.setString(3, "Nicholas Sparks");
                stmt.setString(4, "97804513431766");
                stmt.setString(5, "Romance");
                stmt.setInt(6, 1);
                stmt.executeUpdate();
            }

            // Insert minimal data for checkouts
            String insertCheckoutSql = "INSERT OR IGNORE INTO checkouts (id, patron_id, book_id, checkout_date, due_date, return_date) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertCheckoutSql)) {
                stmt.setInt(1, 1);
                stmt.setInt(2, 1);
                stmt.setInt(3, 1);
                stmt.setString(4, "2025-11-19");
                stmt.setString(5, "2025-12-03");
                stmt.setNull(6, java.sql.Types.VARCHAR);
                stmt.executeUpdate();
            }

            // Insert minimal data for fines (for feature 2 testing)
            String insertFineSql = "INSERT OR IGNORE INTO fines (id, patron_id, checkout_id, amount, paid) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertFineSql)) {
                stmt.setInt(1, 1);
                stmt.setInt(2, 1);
                stmt.setInt(3, 1);
                stmt.setDouble(4, 1.25);  // Example: 5 days overdue at $0.25/day
                stmt.setInt(5, 0);
                stmt.executeUpdate();
            }

            // Insert minimal data for holds (for feature 3 testing)
            String insertHoldSql = "INSERT OR IGNORE INTO holds (id, patron_id, book_id, request_date, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertHoldSql)) {
                stmt.setInt(1, 1);
                stmt.setInt(2, 2);
                stmt.setInt(3, 2);
                stmt.setString(4, "2025-11-20");
                stmt.setString(5, "pending");
                stmt.executeUpdate();
            }

            System.out.println("Library database created and populated with minimal data successfully.");
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
