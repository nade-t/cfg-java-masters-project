package com.cfgmasters.project.service;

import com.cfgmasters.project.model.Book;
import com.cfgmasters.project.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book sellBook(Long id, int salesQuantity) {
        try {
            if (salesQuantity <= 0) {
                throw new IllegalArgumentException("Sales quantity must be greater than 0");
            }

            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book with id: " + id + " not found"));

            int availableCopies = book.getCopiesAvailable();

            if (salesQuantity > availableCopies) {
                throw new IllegalArgumentException("Not enough copies in stock. Number available for purchase: "
                        + availableCopies);
            }

            book.setCopiesAvailable(availableCopies - salesQuantity);
            Book updatedBookStock = bookRepository.save(book);

            log.info("{} copy(ies) of book id {} sold. Remaining stock: {}", salesQuantity, id,
                    updatedBookStock.getCopiesAvailable());

            return updatedBookStock;

        } catch (IllegalArgumentException e) {
            log.error("Unable to process purchase: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unable to process purchase: {}", e.getMessage());
            throw new RuntimeException("Unable to process purchase: " + e.getMessage());
        }
    }
}
