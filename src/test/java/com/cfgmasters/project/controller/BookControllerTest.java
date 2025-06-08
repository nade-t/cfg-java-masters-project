package com.cfgmasters.project.controller;

import com.cfgmasters.project.model.Book;
import com.cfgmasters.project.service.BookService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void whenAddBook_withValidBook_thenBookAdded() throws Exception {
        val rawRequest = """
                {
                    "title": "The Psychology of Money",
                    "author": "Morgan Housel",
                    "price": 10.99,
                    "copiesAvailable": 8
                }
                """;
        val request = post("/books").content(rawRequest).contentType(MediaType.APPLICATION_JSON);

        val mockBook = new Book(1L, "The Psychology of Money", "Morgan Housel", BigDecimal.valueOf((10.99)), 8);
        when(bookService.addBook(any(Book.class))).thenReturn(mockBook);

        mvc.perform(request).andExpect(status().isOk());
    }

    //Validation Test - price must be £1 or more
    @Test
    void whenAddingBookBelowPriceLimit_thenReturnBadRequest() throws Exception {
        val rawRequest = """
                {
                    "title": "The Psychology of Money",
                    "author": "Morgan Housel",
                    "price": 0.99,
                    "copiesAvailable": 8
                }
                """;
        val request = post("/books").content(rawRequest).contentType(MediaType.APPLICATION_JSON);

        val mockBook = new Book(1L, "The Psychology of Money", "Morgan Housel", BigDecimal.valueOf((0.99)), 8);
        when(bookService.addBook(any(Book.class))).thenReturn(mockBook);

        mvc.perform(request).andExpect(status().isBadRequest());
    }


        //Checking if mock service returns list of books
    @Test
    void whenGetAllBooks_thenReturnsListOfBooks() throws Exception {
        List<Book> mockBooks = List.of(
                new Book(1L, "Book One", "Test Author", BigDecimal.valueOf(12.99), 6),
                new Book(2L, "Book Two", "Test Author Two", BigDecimal.valueOf(13.99), 2)
        );
        when(bookService.getAllBooks()).thenReturn(mockBooks);

        mvc.perform(get("/books")).andExpect(status().isOk());
    }

    //Testing a book purchase
    @Test
    void whenPurchaseBookById_thenReturnsStatus200() throws Exception {
        Long id = 1L;
        int purchaseQuantity = 2;

        Book book = new Book();
        book.setId(id);
        book.setCopiesAvailable(6);

        when(bookService.purchaseBook(id, purchaseQuantity)).thenReturn(book);

        mvc.perform(patch("/books/{id}/purchase", id).param("purchaseQuantity", String.valueOf(purchaseQuantity)))
                    .andExpect(status().isOk());
    }

    // Add in test for refund book
    @Test
    void whenRefundBookById_thenReturnsStatus200() throws Exception {
        Long id = 1L;
        int refundQuantity = 1;

        Book book = new Book();
        book.setId(id);
        book.setCopiesAvailable(6);

        when(bookService.refundBook(id, refundQuantity)).thenReturn(book);

        mvc.perform(patch("/books/{id}/refund", id).param("refundQuantity", String.valueOf(refundQuantity)))
                .andExpect(status().isOk());
    }
}