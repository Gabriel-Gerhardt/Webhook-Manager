package com.project.library.contract;

import com.project.library.entities.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookServiceContract {
    List<Book> findAll(String sortBy) throws SQLException;
    void insertBook(Book book) throws SQLException;
    void insertBookList(List<Book> books) throws SQLException;
}
