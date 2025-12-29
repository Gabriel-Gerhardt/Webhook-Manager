package com.project.library.bookStrategy.comparator;

import com.project.library.entities.Book;

import java.util.Comparator;

public class BookComparatorByTitle implements Comparator<Book>{

    @Override
    public int compare(Book book, Book book2){
        return book.getTitle().toUpperCase().compareTo(book2.getTitle().toUpperCase());
    }
}
