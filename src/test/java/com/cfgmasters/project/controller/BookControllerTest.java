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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        val request = post("/books").content(rawRequest).contentType(String.valueOf(MediaType.APPLICATION_JSON));

        val mockBook = new Book(1L, "The Psychology of Money", "Morgan Housel", BigDecimal.valueOf((10.99)), 8);
        when(bookService.addbook(any(Book.class))).thenReturn(mockBook);

        mvc.perform(request).andExpect(status().isOk());
    }

    //Checking if mock service returns list of books
    @Test
    void whenGetAllBooks_thenReturnsListOfBooks() throws Exception {
        List<Book> mockBooks = List.of(
                new Book(1L, "Book One", "Test Author", BigDecimal.valueOf(12.99), 6),
                new Book(1L, "Book One", "Test Author", BigDecimal.valueOf(12.99), 6)
        );
        when(bookService.getAllBooks()).thenReturn(mockBooks);

        mvc.perform(get("/books")).andExpect(status().isOk());
    }
}