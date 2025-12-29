package com.project.library.bookStrategy.consumer;

import com.project.library.entities.Book;

import java.util.function.Consumer;

public class BookConsumerSetTitle implements Consumer<Book> {
    @Override
    public void accept(Book book) {
        book.setTitle(book.getTitle() + " " + book.getPublishYear());
    }
}
