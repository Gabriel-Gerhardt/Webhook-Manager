package com.project.library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.library.contract.BookServiceContract;
import com.project.library.utils.strategy.bookStrategy.comparator.BookComparatorByAuthor;
import com.project.library.utils.strategy.bookStrategy.comparator.BookComparatorByPublishYear;
import com.project.library.utils.strategy.bookStrategy.comparator.BookComparatorByTitle;
import com.project.library.entities.Book;
import com.project.library.utils.exception.IdAlreadyExistsException;
import com.project.library.repo.BookRepo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class BookService implements BookServiceContract {
    private final BookRepo bookRepo;
    private final ObjectMapper objectMapper;
    private final String PAYLOAD_URL = "http://localhost:9000/webhook/payload";
    private final RestClient restClient;
    public BookService (BookRepo bookRepo, ObjectMapper objectMapper, RestClient restClient) {
        this.bookRepo = bookRepo;
        this.objectMapper = objectMapper;
        this.restClient = restClient;
    }

    @Override
    public List<Book> findAll (String sortBy) {
        return bookRepo.findAll().stream()
                .sorted(chooseSortMethod(sortBy))
                .toList();
    }

    @Override
    public void insertBook (Book book) {
        Map<String, Object> body = new HashMap<>();
        body.put("payload", book);
        body.put("event", "create_book");
        try {
            String payload = objectMapper.writeValueAsString(body);
            //assertBookDoesNotExist(book);
            //bookRepo.insertBook(book);
            restClient.post()
                    .uri(PAYLOAD_URL)
                    .contentType(APPLICATION_JSON)
                    .body(payload)
                    .retrieve()
                    .toBodilessEntity();
        } catch (JsonProcessingException e){
            throw new RuntimeException("Erro ao serializar book para JSON", e);
        }
    }

    @Override
    public void insertBookList (List<Book> books) {
        for(Book book : books){
            assertBookDoesNotExist(book);
        }
        bookRepo.insertBookList(books);
    }
    private void assertBookDoesNotExist (Book book) {
        if(bookRepo.findById(book.getId())!=null){
            throw new IdAlreadyExistsException("Book id " + book.getId() + " already exists");
        }
    }
    private Comparator<Book> chooseSortMethod (String sortBy){
        return switch (sortBy) {
            case "author" -> new BookComparatorByAuthor();
            case "title" -> new BookComparatorByTitle();
            default -> new BookComparatorByPublishYear();
        };
    }
}