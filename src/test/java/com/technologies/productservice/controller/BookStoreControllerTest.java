package com.technologies.productservice.controller;

import com.technologies.productservice.model.entity.BookStore;
import com.technologies.productservice.service.BookStoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookStoreControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookStoreService bookStoreService;

    @InjectMocks
    private BookStoreController bookStoreController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookStoreController).build();
    }

    @Test
    void testCreateBookstore() throws Exception {
        BookStore bookstore = new BookStore();
        bookstore.setId(1L);
        bookstore.setName("Test Bookstore");

        when(bookStoreService.createBookstore(any(BookStore.class))).thenReturn(bookstore);

        mockMvc.perform(post("/bookstore")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Bookstore\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Bookstore"));
    }

    @Test
    void testGetAllBookstores() throws Exception {
        List<BookStore> bookstores = new ArrayList<>();
        BookStore bookstore1 = new BookStore();
        bookstore1.setId(1L);
        bookstore1.setName("Bookstore 1");
        bookstores.add(bookstore1);

        when(bookStoreService.getAllBookstores()).thenReturn(bookstores);

        mockMvc.perform(get("/bookstore"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Bookstore 1"));
    }

    @Test
    void testGetBookstoreById() throws Exception {
        BookStore bookstore = new BookStore();
        bookstore.setId(1L);
        bookstore.setName("Test Bookstore");

        when(bookStoreService.getBookstoreById(anyLong())).thenReturn(bookstore);

        mockMvc.perform(get("/bookstore/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Bookstore"));
    }

    @Test
    void testUpdateBookstore() throws Exception {
        BookStore bookstore = new BookStore();
        bookstore.setId(1L);
        bookstore.setName("Updated Bookstore");

        when(bookStoreService.updateBookstore(anyLong(), any(BookStore.class))).thenReturn(bookstore);

        mockMvc.perform(put("/bookstore/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Bookstore\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Bookstore"));
    }

    @Test
    void testDeleteBookstore() throws Exception {
        doNothing().when(bookStoreService).deleteBookstore(anyLong());

        mockMvc.perform(delete("/bookstore/1"))
                .andExpect(status().isOk());
    }
}
