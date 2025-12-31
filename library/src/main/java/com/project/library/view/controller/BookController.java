package com.project.library.view.controller;


import com.project.library.entities.Book;
import com.project.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    
    private final BookService bookService;
    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    
    
    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false) String sortBy) {
        return bookService.findAll(sortBy);
    }

    @PostMapping
    public void postBook(@RequestBody Book book) {
        bookService.insertBook(book);
    }

    @PostMapping("/batch")
    public void postBookBatch(@RequestBody List<Book> books) {
        bookService.insertBookList(books);
    }
}
