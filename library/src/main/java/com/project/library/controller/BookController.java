package com.project.library.controller;


import com.project.library.entities.Book;
import com.project.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    
    private final BookService bookService;
    public BookController(BookService bookRepo){
        this.bookService = bookRepo;
    }
    
    
    @GetMapping
    public List<Book> getAllBooks() throws SQLException {
        return bookService.findAll();
    }

    @PostMapping
    public void postBook(@RequestBody Book book) throws SQLException, IOException, InterruptedException {
        bookService.insertBook(book);
    }

    @PostMapping("/batch")
    public void postBookBatch(@RequestBody List<Book> books) throws SQLException{
        bookService.insertBookList(books);
    }
}
