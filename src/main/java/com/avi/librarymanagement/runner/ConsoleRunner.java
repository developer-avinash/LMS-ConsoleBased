package com.avi.librarymanagement.runner;

import com.avi.librarymanagement.model.Book;
import com.avi.librarymanagement.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final BookService bookService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleRunner(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) {

        boolean running = true;

        while (running) {

            showMenu();

            try {

                System.out.print("Enter your choice: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        addBook();
                        break;

                    case 2:
                        viewAllBooks();
                        break;

                    case 3:
                        searchBookById();
                        break;

                    case 4:
                        updateBook();
                        break;

                    case 5:
                        deleteBook();
                        break;

                    case 6:
                        searchBookByTitle();
                        break;

                    case 7:
                        filterBooksByAuthor();
                        break;

                    case 8:
                        filterBooksByCategory();
                        break;

                    case 9:
                        running = false;
                        System.out.println(
                                "\nThank you for using Library Management System!"
                        );
                        break;

                    default:
                        System.out.println(
                                "\nInvalid choice! Please try again."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                        "\nError: " + e.getMessage()
                );
            }
        }
    }

    private void showMenu() {

        System.out.println("\n======================================");
        System.out.println("      LIBRARY MANAGEMENT SYSTEM");
        System.out.println("======================================");

        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book By ID");
        System.out.println("4. Update Book");
        System.out.println("5. Delete Book");
        System.out.println("6. Search Book By Title");
        System.out.println("7. Filter Books By Author");
        System.out.println("8. Filter Books By Category");
        System.out.println("9. Exit");

        System.out.println("======================================");
    }

    private void addBook() {

        System.out.println("\n----- ADD BOOK -----");

        System.out.print("Enter Book ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        Book book = new Book(
                id,
                title,
                author,
                category,
                price,
                quantity
        );

        bookService.addBook(book);

        System.out.println("\nBook added successfully!");
    }

    private void viewAllBooks() {

        System.out.println("\n----- ALL BOOKS -----");

        List<Book> books = bookService.getAllBooks();

        displayBooks(books);
    }

    private void searchBookById() {

        System.out.println("\n----- SEARCH BOOK BY ID -----");

        System.out.print("Enter Book ID: ");

        int id = Integer.parseInt(scanner.nextLine());

        Book book = bookService.getBookById(id);

        System.out.println(book);
    }

    private void updateBook() {

        System.out.println("\n----- UPDATE BOOK -----");

        System.out.print("Enter Book ID: ");

        int id = Integer.parseInt(scanner.nextLine());

        Book existingBook = bookService.getBookById(id);

        System.out.println(
                "\nCurrent Book Details:"
        );

        System.out.println(existingBook);

        System.out.print("\nEnter New Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter New Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter New Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter New Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter New Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        Book updatedBook = new Book(
                id,
                title,
                author,
                category,
                price,
                quantity
        );

        bookService.updateBook(updatedBook);

        System.out.println("\nBook updated successfully!");
    }

    private void deleteBook() {

        System.out.println("\n----- DELETE BOOK -----");

        System.out.print("Enter Book ID: ");

        int id = Integer.parseInt(scanner.nextLine());

        bookService.deleteBook(id);

        System.out.println("\nBook deleted successfully!");
    }

    private void searchBookByTitle() {

        System.out.println("\n----- SEARCH BOOK BY TITLE -----");

        System.out.print("Enter Book Title: ");

        String title = scanner.nextLine();

        List<Book> books =
                bookService.searchBooksByTitle(title);

        displayBooks(books);
    }

    private void filterBooksByAuthor() {

        System.out.println("\n----- FILTER BOOKS BY AUTHOR -----");

        System.out.print("Enter Author Name: ");

        String author = scanner.nextLine();

        List<Book> books =
                bookService.filterBooksByAuthor(author);

        displayBooks(books);
    }

    private void filterBooksByCategory() {

        System.out.println("\n----- FILTER BOOKS BY CATEGORY -----");

        System.out.print("Enter Category: ");

        String category = scanner.nextLine();

        List<Book> books =
                bookService.filterBooksByCategory(category);

        displayBooks(books);
    }

    private void displayBooks(List<Book> books) {

        if (books.isEmpty()) {

            System.out.println("\nNo books found.");

            return;
        }

        for (Book book : books) {

            System.out.println(book);
        }
    }
}