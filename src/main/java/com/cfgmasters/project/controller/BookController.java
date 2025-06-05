package com.cfgmasters.project.controller;


import com.cfgmasters.project.model.Book;
import com.cfgmasters.project.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        log.info("Adding new book: {}", book);
        return ResponseEntity.ok(bookService.addbook(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("Fetching all books");
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}
