package com.technologies.productservice.controller;


import com.technologies.productservice.model.entity.BookStore;
import com.technologies.productservice.service.BookStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookstore")
public class BookStoreController {

    private final BookStoreService bookstoreService;

    @PostMapping
    public BookStore createBookstore(@RequestBody BookStore bookstore) {
        return bookstoreService.createBookstore(bookstore);
    }

    @GetMapping
    public List<BookStore> getAllBookstores() {
        return bookstoreService.getAllBookstores();
    }

    @GetMapping("/{id}")
    public BookStore getBookstoreById(@PathVariable Long id) {
        return bookstoreService.getBookstoreById(id);
    }

    @PutMapping("/{id}")
    public BookStore updateBookstore(@PathVariable Long id, @RequestBody BookStore updatedBookstore) {
        return bookstoreService.updateBookstore(id, updatedBookstore);
    }

    @DeleteMapping("/{id}")
    public void deleteBookstore(@PathVariable Long id) {
        bookstoreService.deleteBookstore(id);
    }
}

