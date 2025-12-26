package com.project.library.controller;


import com.project.library.repo.BookRepo;
import com.project.library.entities.Book;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    
    private final BookRepo bookRepo;
    public BookController(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }
    
    
    @GetMapping
    public List<Book> getAllBooks() throws SQLException {
        return bookRepo.FindAll();
    }

    @PostMapping
    public void postBook(@RequestBody Book book) throws SQLException, IOException, InterruptedException {
        bookRepo.CreateBook(book);
    }

    @PostMapping("/batch")
    public void postBookBatch(@RequestBody List<Book> books) throws SQLException{
        bookRepo.InsertBookList(books);
    }
}
