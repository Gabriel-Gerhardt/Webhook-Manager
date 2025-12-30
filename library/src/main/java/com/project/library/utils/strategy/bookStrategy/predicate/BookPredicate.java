package com.project.library.utils.strategy.bookStrategy.predicate;

import com.project.library.entities.Book;

import java.util.function.Predicate;

public class BookPredicate implements Predicate<Book> {

    @Override
    public boolean test (Book book) {
        return book.getPublishYear() > 1900;
    }
}
