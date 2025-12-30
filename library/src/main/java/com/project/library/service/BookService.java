package com.project.library.service;

import com.project.library.entities.Book;
import com.project.library.exception.IdAlreadyExistsException;
import com.project.library.repo.BookRepo;

import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;

    public BookService (BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }
    public List<Book> findAll(Comparator<Book> comp) throws SQLException {
        return bookRepo.findAll().stream()
                .sorted(comp)
                .toList();
    }
    public void insertBook (Book book) throws SQLException {
        assertBookDoesNotExist(book);
        bookRepo.insertBook(book);
    }
    public void insertBookList (List<Book> books) throws SQLException {
        for(Book book : books){
            assertBookDoesNotExist(book);
        }
        bookRepo.insertBookList(books);
    }
    private void assertBookDoesNotExist(Book book) throws SQLException {
        if(bookRepo.findById(book.getId())!=null){
            throw new IdAlreadyExistsException("Book id " + book.getId() + " already exists");
        }
    }
}