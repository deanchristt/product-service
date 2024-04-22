package com.technologies.productservice.service;

import com.technologies.productservice.model.entity.Book;
import com.technologies.productservice.model.entity.BookStore;
import com.technologies.productservice.repository.BookRepository;
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

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookStoreRepository bookStoreRepository;

    @Mock
    private BookStoreService bookStoreService;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        Long bookstoreId = 1L;

        when(bookStoreRepository.findById(anyLong())).thenReturn(Optional.of(new BookStore()));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book createdBook = bookService.createBook(bookstoreId, book);

        assertNotNull(createdBook);
        assertEquals("Test Book", createdBook.getTitle());
    }

    @Test
    void testGetAllBooksByBookstore() {
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());

        when(bookRepository.findBooksByBookStore_Id(anyLong())).thenReturn(books);

        List<Book> foundBooks = bookService.getAllBooksByBookstore(1L);

        assertNotNull(foundBooks);
        assertEquals(2, foundBooks.size());
    }

    @Test
    void testGetBookById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        Book foundBook = bookService.getBookById(1L);

        assertNotNull(foundBook);
        assertEquals("Test Book", foundBook.getTitle());
    }

    @Test
    void testUpdateBook() {
        Book existingBook = new Book();
        existingBook.setId(1L);
        existingBook.setTitle("Existing Book");

        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Book");

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        Book savedBook = bookService.updateBook(1L, updatedBook);

        assertNotNull(savedBook);
        assertEquals("Updated Book", savedBook.getTitle());
    }

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setId(1L);

        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(any(Book.class));

        assertDoesNotThrow(() -> bookService.deleteBook(1L));
    }
}
