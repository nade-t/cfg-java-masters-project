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

    public Book addBook(Book book) {
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

    public Book purchaseBook(Long id, int purchaseQuantity) {
        try {
            if (purchaseQuantity <= 0) {
                throw new IllegalArgumentException("Purchase quantity must be greater than 0");
            }

            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book with id: " + id + " not found"));

            int availableCopies = book.getCopiesAvailable();

            if (purchaseQuantity > availableCopies) {
                throw new IllegalArgumentException("Not enough copies in stock. Number available for purchase: "
                        + availableCopies);
            }

            book.setCopiesAvailable(availableCopies - purchaseQuantity);
            Book updatedBookStock = bookRepository.save(book);

            log.info("{} copy(ies) of book id {} sold. Remaining stock: {}", purchaseQuantity, id,
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

    public Book refundBook(Long id, int refundQuantity) {
        try {
            if (refundQuantity <= 0) {
                throw new IllegalArgumentException("Refund quantity must be greater than 0");
            }

            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book with id: " + id + " not found in inventory"));

            /*

             * Refund method does not need to check for stock levels.
             * Book items with a 0 copiesAvailable quantity will appear as "out of stock" and their stock levels
             * are still accessible to process refunds.
             */
            int availableCopies = book.getCopiesAvailable();
            book.setCopiesAvailable(availableCopies + refundQuantity);
            log.info("Refund processed and stock levels of book id {} updated", id);
            Book updatedBookStock = bookRepository.save(book);
            return updatedBookStock;

        } catch (IllegalArgumentException e) {
            log.error("Unable to process refund: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unable to process refund: {}", e.getMessage());
            throw new RuntimeException("Unable to process refund: " + e.getMessage());
        }
    }
}
