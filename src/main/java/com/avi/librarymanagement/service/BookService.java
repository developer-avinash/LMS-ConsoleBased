package com.avi.librarymanagement.service;

import com.avi.librarymanagement.exception.BookNotFoundException;
import com.avi.librarymanagement.exception.DuplicateBookException;
import com.avi.librarymanagement.model.Book;
import com.avi.librarymanagement.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Add Book
    public Book addBook(Book book) {

        if (bookRepository.existsById(book.getId())) {
            throw new DuplicateBookException(
                    "Book already exists with ID: " + book.getId()
            );
        }

        return bookRepository.save(book);
    }

    // Get All Books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get Book By ID
    public Book getBookById(int id) {

        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException(
                                "Book not found with ID: " + id
                        )
                );
    }

    // Update Book
    public Book updateBook(Book book) {

        if (!bookRepository.existsById(book.getId())) {
            throw new BookNotFoundException(
                    "Book not found with ID: " + book.getId()
            );
        }

        return bookRepository.save(book);
    }

    // Delete Book
    public void deleteBook(int id) {

        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(
                    "Book not found with ID: " + id
            );
        }

        bookRepository.deleteById(id);
    }

    // Search Books By Title
    public List<Book> searchBooksByTitle(String title) {

        return bookRepository
                .findByTitleContainingIgnoreCase(title);
    }

    // Filter Books By Author
    public List<Book> filterBooksByAuthor(String author) {

        return bookRepository
                .findByAuthorIgnoreCase(author);
    }

    // Filter Books By Category
    public List<Book> filterBooksByCategory(String category) {

        return bookRepository
                .findByCategoryIgnoreCase(category);
    }
}