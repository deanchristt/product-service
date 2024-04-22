package com.technologies.productservice.service;

import com.technologies.productservice.model.entity.BookStore;
import com.technologies.productservice.repository.BookStoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BookStoreServiceTest {

    @Mock
    private BookStoreRepository bookstoreRepository;

    @InjectMocks
    private BookStoreService bookstoreService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateBookstore() {
        BookStore bookstore = new BookStore();
        bookstore.setName("Test Bookstore");

        when(bookstoreRepository.save(any(BookStore.class))).thenReturn(bookstore);

        BookStore createdBookstore = bookstoreService.createBookstore(bookstore);

        assertNotNull(createdBookstore);
        assertEquals("Test Bookstore", createdBookstore.getName());
    }

    @Test
    void testGetAllBookstores() {
        List<BookStore> bookstores = new ArrayList<>();
        bookstores.add(new BookStore());
        bookstores.add(new BookStore());

        when(bookstoreRepository.findAll()).thenReturn(bookstores);

        List<BookStore> foundBookstores = bookstoreService.getAllBookstores();

        assertNotNull(foundBookstores);
        assertEquals(2, foundBookstores.size());
    }

    @Test
    void testGetBookstoreById() {
        BookStore bookstore = new BookStore();
        bookstore.setId(1L);
        bookstore.setName("Test Bookstore");

        when(bookstoreRepository.findById(anyLong())).thenReturn(Optional.of(bookstore));

        BookStore foundBookstore = bookstoreService.getBookstoreById(1L);

        assertNotNull(foundBookstore);
        assertEquals("Test Bookstore", foundBookstore.getName());
    }

    @Test
    void testUpdateBookstore() {
        BookStore existingBookstore = new BookStore();
        existingBookstore.setId(1L);
        existingBookstore.setName("Existing Bookstore");

        BookStore updatedBookstore = new BookStore();
        updatedBookstore.setName("Updated Bookstore");

        when(bookstoreRepository.findById(anyLong())).thenReturn(Optional.of(existingBookstore));
        when(bookstoreRepository.save(any(BookStore.class))).thenReturn(updatedBookstore);

        BookStore savedBookstore = bookstoreService.updateBookstore(1L, updatedBookstore);

        assertNotNull(savedBookstore);
        assertEquals("Updated Bookstore", savedBookstore.getName());
    }

    @Test
    void testDeleteBookstore() {
        BookStore bookstore = new BookStore();
        bookstore.setId(1L);

        when(bookstoreRepository.findById(anyLong())).thenReturn(Optional.of(bookstore));
        doNothing().when(bookstoreRepository).delete(any(BookStore.class));

        assertDoesNotThrow(() -> bookstoreService.deleteBookstore(1L));
    }
}
