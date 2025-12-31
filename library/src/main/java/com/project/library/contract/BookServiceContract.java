package com.project.library.contract;

import com.project.library.entities.Book;

import java.util.List;

public interface BookServiceContract {
    List<Book> findAll(String sortBy);
    void insertBook(Book book);
    void insertBookList(List<Book> books);
}
