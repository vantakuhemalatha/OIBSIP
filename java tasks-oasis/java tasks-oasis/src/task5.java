import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DigitalAsset {
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;

    public DigitalAsset(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true; // Assume books are initially available
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class User {
    private String username;
    private String email;
    private boolean isAdmin;

    public User(String username, String email, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}

class Library {
    private List<DigitalAsset> catalog;
    private List<User> users;

    public Library() {
        catalog = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addDigitalAsset(DigitalAsset asset) {
        catalog.add(asset);
    }

    public boolean removeDigitalAsset(String title) {
        for (DigitalAsset asset : catalog) {
            if (asset.getTitle().equalsIgnoreCase(title)) {
                catalog.remove(asset);
                return true; // Asset removed successfully
            }
        }
        return false; // Asset not found
    }

    public boolean updateDigitalAsset(String title, DigitalAsset updatedAsset) {
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).getTitle().equalsIgnoreCase(title)) {
                catalog.set(i, updatedAsset);
                return true; // Asset updated successfully
            }
        }
        return false; // Asset not found
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public List<DigitalAsset> getCatalog() {
        return catalog;
    }

    public List<DigitalAsset> viewBooks() {
        return catalog; // Users can view the entire catalog
    }

    public List<DigitalAsset> searchByTitle(String title) {
        List<DigitalAsset> results = new ArrayList<>();
        for (DigitalAsset asset : catalog) {
            if (asset.getTitle().equalsIgnoreCase(title)) {
                results.add(asset);
            }
        }
        return results;
    }

    public boolean issueBook(String title) {
        // Simulate the process of issuing a book (marking it as unavailable)
        for (DigitalAsset asset : catalog) {
            if (asset.getTitle().equalsIgnoreCase(title)) {
                // Assuming a boolean field "isAvailable" in DigitalAsset
                if (asset.isAvailable()) {
                    asset.setAvailable(false);
                    return true; // Book issued successfully
                } else {
                    return false; // Book is already issued
                }
            }
        }
        return false; // Book not found
    }

    public boolean returnBook(String title) {
        // Simulate the process of returning a book (marking it as available)
        for (DigitalAsset asset : catalog) {
            if (asset.getTitle().equalsIgnoreCase(title)) {
                // Assuming a boolean field "isAvailable" in DigitalAsset
                if (!asset.isAvailable()) {
                    asset.setAvailable(true);
                    return true; // Book returned successfully
                } else {
                    return false; // Book is already available
                }
            }
        }
        return false; // Book not found
    }
}

public class task5 {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Add admin user
        User admin = new User("admin", "admin@example.com", true);
        library.addUser(admin);

        // Add digital assets to the library
        library.addDigitalAsset(new DigitalAsset("Book 1", "Author 1", "Fiction"));
        library.addDigitalAsset(new DigitalAsset("Book 2", "Author 2", "Non-fiction"));

        // User and Admin actions
        boolean continueActions = true;
        while (continueActions) {
            System.out.println("1. View Books");
            System.out.println("2. Search for a Book");
            System.out.println("3. Issue a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Admin Menu (Admin Only)");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // View Books
                    List<DigitalAsset> books = library.viewBooks();
                    System.out.println("Catalog of Books:");
                    for (DigitalAsset book : books) {
                        System.out.println("Title: " + book.getTitle());
                        System.out.println("Author: " + book.getAuthor());
                        System.out.println("Genre: " + book.getGenre());
                        System.out.println("Status: " + (book.isAvailable() ? "Available" : "Issued"));
                        System.out.println();
                    }
                    break;

                case 2:
                    // Search for a Book
                    System.out.print("Enter Title to Search: ");
                    String searchTitle = scanner.nextLine();
                    List<DigitalAsset> searchResults = library.searchByTitle(searchTitle);
                    if (searchResults.isEmpty()) {
                        System.out.println("No matching books found.");
                    } else {
                        System.out.println("Search Results:");
                        for (DigitalAsset result : searchResults) {
                            System.out.println("Title: " + result.getTitle());
                            System.out.println("Author: " + result.getAuthor());
                            System.out.println("Genre: " + result.getGenre());
                            System.out.println("Status: " + (result.isAvailable() ? "Available" : "Issued"));
                            System.out.println();
                        }
                    }
                    break;

                case 3:
                    // Issue a Book
                    System.out.print("Enter Title to Issue: ");
                    String issueTitle = scanner.nextLine();
                    if (library.issueBook(issueTitle)) {
                        System.out.println("Book issued successfully.");
                    } else {
                        System.out.println("Book not found or already issued.");
                    }
                    break;

                case 4:
                    // Return a Book
                    System.out.print("Enter Title to Return: ");
                    String returnTitle = scanner.nextLine();
                    if (library.returnBook(returnTitle)) {
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Book not found or already available.");
                    }
                    break;

                case 5:
                    // Admin Menu
                    if (admin.isAdmin()) {
                        boolean continueAdminActions = true;
                        while (continueAdminActions) {
                            System.out.println("Admin Menu:");
                            System.out.println("1. Add New Asset");
                            System.out.println("2. Update Asset");
                            System.out.println("3. Delete Asset");
                            System.out.println("4. Exit Admin Menu");
                            System.out.print("Select an option: ");
                            int adminChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (adminChoice) {
                                case 1:
                                    // Add new asset
                                    System.out.print("Enter Title: ");
                                    String title = scanner.nextLine();
                                    System.out.print("Enter Author: ");
                                    String author = scanner.nextLine();
                                    System.out.print("Enter Genre: ");
                                    String genre = scanner.nextLine();
                                    library.addDigitalAsset(new DigitalAsset(title, author, genre));
                                    System.out.println("Asset added successfully.");
                                    break;

                                case 2:
                                    // Update asset
                                    System.out.print("Enter Title to Update: ");
                                    String assetTitle = scanner.nextLine();
                                    DigitalAsset updatedAsset = null;

                                    for (DigitalAsset asset : library.getCatalog()) {
                                        if (asset.getTitle().equalsIgnoreCase(assetTitle)) {
                                            System.out.print("Enter New Title: ");
                                            String newTitle = scanner.nextLine();
                                            System.out.print("Enter New Author: ");
                                            String newAuthor = scanner.nextLine();
                                            System.out.print("Enter New Genre: ");
                                            String newGenre = scanner.nextLine();
                                            updatedAsset = new DigitalAsset(newTitle, newAuthor, newGenre);
                                        }
                                    }

                                    if (updatedAsset != null) {
                                        if (library.updateDigitalAsset(assetTitle, updatedAsset)) {
                                            System.out.println("Asset updated successfully.");
                                        } else {
                                            System.out.println("Asset not found.");
                                        }
                                    } else {
                                        System.out.println("Asset not found.");
                                    }
                                    break;

                                case 3:
                                    // Delete asset
                                    System.out.print("Enter Title to Delete: ");
                                    String assetTitleToDelete = scanner.nextLine();
                                    if (library.removeDigitalAsset(assetTitleToDelete)) {
                                        System.out.println("Asset deleted successfully.");
                                    } else {
                                        System.out.println("Asset not found.");
                                    }
                                    break;

                                case 4:
                                    // Exit admin menu
                                    continueAdminActions = false;
                                    break;

                                default:
                                    System.out.println("Invalid option.");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("You are not authorized to access the admin menu.");
                    }
                    break;

                case 6:
                    // Exit
                    continueActions = false;
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }

        // Close the Scanner when done
        scanner.close();
    }
}