import java.util.*;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class User {
    private int id;
    private String name;
    private List<Book> borrowedBooks;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }
}

class Library {
    private Map<Integer, Book> books;
    private Map<Integer, User> users;

    public Library() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void borrowBook(int userId, int bookId) {
        User user = users.get(userId);
        Book book = books.get(bookId);
        if (user != null && book != null && book.isAvailable()) {
            user.borrowBook(book);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is not available or user does not exist.");
        }
    }

    public void returnBook(int userId, int bookId) {
        User user = users.get(userId);
        Book book = books.get(bookId);
        if (user != null && book != null && user.getBorrowedBooks().contains(book)) {
            user.returnBook(book);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("User or book not found or book not borrowed by user.");
        }
    }

    public Map<Integer, User> getUsers() {
        return users;
    }
}

public class App {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book(1, "Java Programming", "John Doe");
        Book book2 = new Book(2, "Python Programming", "Jane Smith");
        library.addBook(book1);
        library.addBook(book2);

        User user1 = new User(1, "Keerthana");
        User user2 = new User(2, "Bob");
        library.addUser(user1);
        library.addUser(user2);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID:");
        int userId = scanner.nextInt();
        System.out.println("Enter book ID to borrow:");
        int bookIdToBorrow = scanner.nextInt();
        library.borrowBook(userId, bookIdToBorrow);

        System.out.println("Enter user ID to return book:");
        userId = scanner.nextInt();
        System.out.println("Enter book ID to return:");
        int bookIdToReturn = scanner.nextInt();
        library.returnBook(userId, bookIdToReturn);

        System.out.println("Enter user ID to list borrowed books:");
        userId = scanner.nextInt();
        listBorrowedBooks(library, userId);

        scanner.close();
    }

    public static void listBorrowedBooks(Library library, int userId) {
        User user = library.getUsers().get(userId);
        if (user != null) {
            List<Book> borrowedBooks = user.getBorrowedBooks();
            if (!borrowedBooks.isEmpty()) {
                System.out.println("Books borrowed by " + user.getName() + ":");
                for (Book book : borrowedBooks) {
                    System.out.println(book.getTitle() + " by " + book.getAuthor());
                }
            } else {
                System.out.println("No books borrowed by " + user.getName());
            }
        } else {
            System.out.println("User not found.");
        }
    }
}
