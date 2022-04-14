package com.example.Lab1.service;

import com.example.Lab1.model.Book;
import com.example.Lab1.model.dto.BookDto;
import com.example.Lab1.model.enumerations.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public List<Book> getAll();

    public void deleteById(Long id);

    public Optional<Book> edit(Long id, BookDto book);

    public Optional<Book> save(BookDto book);

    public Optional<Book> takeBook(Long bookId);

    public Optional<Book> findById(Long id);
}
