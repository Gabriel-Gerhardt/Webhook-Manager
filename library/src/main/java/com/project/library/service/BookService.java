package com.project.library.service;

import com.project.library.bookStrategy.comparator.BookComparatorByPublishYear;
import com.project.library.bookStrategy.comparator.BookComparatorByTitle;
import com.project.library.entities.Book;
import com.project.library.repo.BookRepo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }
    public List<Book> findAll() throws SQLException {
        List<Book> lista = bookRepo.findAll();
        lista.sort(new BookComparatorByTitle());
        return lista;
    }

    public List<Book> findAllSortedById() throws SQLException {
        List<Book> lista = bookRepo.findAll();
        lista.sort((b1,b2)-> {
            return Long.compare(b1.getId(), b2.getId());
        });
        lista.removeIf((b1)->{
            return b1.getPublishYear()>1900;
        });
        return lista;
    }
    public List<Book> findAllSortedByPublishYear() throws SQLException {
        List<Book> lista = bookRepo.findAll();
        lista.sort(new BookComparatorByPublishYear());
        return lista;
    }
    public void insertBook(Book book) throws SQLException, IOException, InterruptedException {
        if(bookRepo.findById(book.getId())!=null){
            throw new IllegalArgumentException("Book id already exists");
        }
        else{
            bookRepo.insertBook(book);
        }

    }
    public void insertBookList(List<Book> books) throws SQLException {
        for(Book book : books){
            if(bookRepo.findById(book.getId())!=null){
                throw new IllegalArgumentException("Book id already exists");
            }
        }
        bookRepo.insertBookList(books);
    }
}
