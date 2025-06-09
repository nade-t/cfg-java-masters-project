package com.cfgmasters.project.service;

import com.cfgmasters.project.model.Book;
import com.cfgmasters.project.repository.BookRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;
    @MockitoBean
    private BookRepository bookRepository;

    public static class Datasource {

        public static List<Book> getSampleBooks() {
            // Create Book objects using no-arg constructor and setters, which aren't filtered out by the stream in BookService.
            Book book1 = new Book();
            book1.setTitle("Carpe Jugulum");
            book1.setAuthor("Terry Pratchett");
            book1.setPrice(BigDecimal.valueOf(8.99));
            book1.setCopiesAvailable(3);

            Book book2 = new Book();
            book2.setTitle("Interesting Times");
            book2.setAuthor("Terry Pratchett");
            book2.setPrice(BigDecimal.valueOf(7.99));
            book2.setCopiesAvailable(2);

            return List.of(book1, book2);
        }
    }

    // Happy path test, when all records are returned.
    @Test
    void when_repositoryReturnsBooks_then_returnsBooks() {

        when(bookRepository.findAll()).thenReturn(Datasource.getSampleBooks());

        val actual = bookService.getAllBooks();

        assertEquals(2, actual.size());
    }

    // Incomplete records test, purposefully fails.
    @Test
    void when_repositoryReturnIncomplete_then_throwException() {

        when(bookRepository.findAll()).thenReturn(Datasource.getSampleBooks());

        val actual = bookService.getAllBooks();

        assertEquals(4, actual.size());
    }

    // Failed test, when no records are returned.
    @Test
    void when_repositoryThrowsException_then_returnEmptyList() {
        when(bookRepository.findAll()).thenThrow(RuntimeException.class);

        val actual = bookService.getAllBooks();

        assertTrue(actual.isEmpty());
    }
}