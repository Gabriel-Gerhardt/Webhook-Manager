package com.project.library.service;

import com.project.library.contract.BookServiceContract;
import com.project.library.utils.strategy.bookStrategy.comparator.BookComparatorByAuthor;
import com.project.library.utils.strategy.bookStrategy.comparator.BookComparatorByPublishYear;
import com.project.library.utils.strategy.bookStrategy.comparator.BookComparatorByTitle;
import com.project.library.entities.Book;
import com.project.library.utils.exception.IdAlreadyExistsException;
import com.project.library.repo.BookRepo;

import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class BookService implements BookServiceContract {
    private final BookRepo bookRepo;

    public BookService (BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> findAll (String sortBy) {
        return bookRepo.findAll().stream()
                .sorted(chooseSortMethod(sortBy))
                .toList();
    }

    @Override
    public void insertBook (Book book) {
        assertBookDoesNotExist(book);
        bookRepo.insertBook(book);
    }

    @Override
    public void insertBookList (List<Book> books) {
        for(Book book : books){
            assertBookDoesNotExist(book);
        }
        bookRepo.insertBookList(books);
    }
    private void assertBookDoesNotExist (Book book) {
        if(bookRepo.findById(book.getId())!=null){
            throw new IdAlreadyExistsException("Book id " + book.getId() + " already exists");
        }
    }
    private Comparator<Book> chooseSortMethod (String sortBy){
        return switch (sortBy) {
            case "author" -> new BookComparatorByAuthor();
            case "title" -> new BookComparatorByTitle();
            default -> new BookComparatorByPublishYear();
        };
    }
}