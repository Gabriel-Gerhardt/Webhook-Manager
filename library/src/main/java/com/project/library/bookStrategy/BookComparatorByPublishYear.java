package com.project.library.bookStrategy;

import com.project.library.entities.Book;

import java.util.Comparator;

public class BookComparatorByPublishYear implements Comparator<Book> {

    @Override
    public int compare(Book book, Book book2){
        return Integer.compare(book.getPublishYear(), book2.getPublishYear());
    }

}
