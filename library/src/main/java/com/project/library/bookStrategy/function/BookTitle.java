package com.project.library.bookStrategy.function;

import com.project.library.entities.Book;

import java.util.function.Function;

public class BookTitle implements Function<Book,String> {
    @Override
    public String apply(Book book) {
        return book.getTitle().toLowerCase().trim();
    }
}
