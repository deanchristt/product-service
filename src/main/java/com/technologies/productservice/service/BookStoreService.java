package com.technologies.productservice.service;

import com.technologies.productservice.model.entity.BookStore;
import com.technologies.productservice.repository.BookStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookStoreService {

    private final BookStoreRepository bookstoreRepository;

    public BookStore createBookstore(BookStore bookstore) {
        return bookstoreRepository.save(bookstore);
    }

    public List<BookStore> getAllBookstores() {
        return bookstoreRepository.findAll();
    }

    public BookStore getBookstoreById(Long id) {
        return bookstoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bookstore not found"));
    }

    public BookStore updateBookstore(Long id, BookStore updatedBookstore) {
        BookStore existingBookstore = bookstoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bookstore not found"));
        existingBookstore.setName(updatedBookstore.getName());
        existingBookstore.setLocation(updatedBookstore.getLocation());
        return bookstoreRepository.save(existingBookstore);
    }

    public void deleteBookstore(Long id) {
        BookStore bookstore = bookstoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bookstore not found"));
        bookstoreRepository.delete(bookstore);
    }
}

