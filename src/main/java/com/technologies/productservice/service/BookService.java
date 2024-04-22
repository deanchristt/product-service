package com.technologies.productservice.service;

import com.technologies.productservice.model.entity.Book;
import com.technologies.productservice.repository.BookRepository;
import com.technologies.productservice.repository.BookStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final BookStoreRepository bookStoreRepository;

    private final BookStoreService bookStoreService;

    public Book createBook(Long bookstoreId, Book book) {
        // Check if the bookstore exists
        return bookStoreRepository.findById(bookstoreId)
                .map(bookstore -> {
                    book.setBookStore(bookstore);
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Bookstore not found"));
    }

    public List<Book> getAllBooksByBookstore(Long bookstoreId) {
        return bookRepository.findBooksByBookStore_Id(bookstoreId);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book updateBook(Long bookId, Book updatedBook) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }
}