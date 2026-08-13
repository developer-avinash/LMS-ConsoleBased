package com.avi.librarymanagement.repository;

import com.avi.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Search books by title
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Filter books by author
    List<Book> findByAuthorIgnoreCase(String author);

    // Filter books by category
    List<Book> findByCategoryIgnoreCase(String category);
}