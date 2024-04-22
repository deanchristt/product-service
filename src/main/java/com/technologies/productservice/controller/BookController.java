package com.technologies.productservice.controller;

import com.technologies.productservice.model.entity.Book;
import com.technologies.productservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/{bookstoreId}/books")
    public Book createBook(@PathVariable Long bookstoreId, @RequestBody Book book) {
        return bookService.createBook(bookstoreId, book);
    }

    @GetMapping("/{bookstoreId}/books")
    public List<Book> getAllBooksByBookstore(@PathVariable Long bookstoreId) {
        return bookService.getAllBooksByBookstore(bookstoreId);
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        return bookService.updateBook(bookId, updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }
}

