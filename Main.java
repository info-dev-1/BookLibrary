public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding books and patrons
        library.addBook(new Book("Java Programming", "John Doe", "1234567890", Genre.NON_FICTION));
        library.addBook(new Book("Python Basics", "Jane Smith", "0987654321", Genre.NON_FICTION));
        library.addPatron(new Patron("Alice", 101));
        library.addPatron(new Patron("Bob", 102));

        // Displaying books and patrons
        library.displayBooks();
        library.displayPatrons();

        // Borrowing a book
        library.borrowBook("Java Programming", "Alice");
        library.borrowBook("Python Basics", "Bob");

        // Displaying books after borrowing
        library.displayBooks();

        // Returning a book
        library.returnBook("Java Programming");

        // Displaying books after returning
        library.displayBooks();


        // Future sprint / reading-list-input - TODO: write code here (perhaps in a loop) to instantiate and call methods on a Controller instance,
        // in order to get reading lists from the user (or a database) and to perform the comparison on those lists.


    }
}
