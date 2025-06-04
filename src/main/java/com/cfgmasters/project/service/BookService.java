package com.cfgmasters.project.service;

import com.cfgmasters.project.model.Book;
import com.cfgmasters.project.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book addbook(Book book) {
        try {
            Book bookSaved = bookRepository.save(book);
            log.info("Successfully added book: {}", bookSaved.getTitle());
            return bookSaved;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
