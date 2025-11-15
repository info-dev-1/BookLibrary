
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Adding books and patrons
        library.addBook(new Book("Java Programming", "John Doe", "1234567890", Genre.NON_FICTION));
        library.addBook(new Book("Python Basics", "Jane Smith", "0987654321", Genre.NON_FICTION));
        library.addPatron(new Patron("Alice", 101));
        library.addPatron(new Patron("Bob", 102));

       
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Display Books");
            System.out.println("2. Display Patrons");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Send Overdue Notifications");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    library.displayPatrons();
                    break;
                case 3:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter patron name: ");
                    String patronName = scanner.nextLine();
                    library.borrowBook(title, patronName);
                    break;
                case 4:
                    System.out.print("Enter book title: ");
                    title = scanner.nextLine();
                    library.returnBook(title);
                    break;
                case 5:
                    library.sendOverdueNotifications();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

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
