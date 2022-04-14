package com.example.Lab1.service.impl;

import com.example.Lab1.model.Author;
import com.example.Lab1.model.Book;
import com.example.Lab1.model.dto.BookDto;
import com.example.Lab1.model.exceptions.AuthorNotFoundException;
import com.example.Lab1.model.exceptions.BookNotAvailable;
import com.example.Lab1.model.exceptions.BookNotFoundException;
import com.example.Lab1.repository.AuthorRepository;
import com.example.Lab1.repository.BookRepository;
import com.example.Lab1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(bookDto.getCategory());

        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));

        book.setAuthor(author);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto book) {
        Author author = authorRepository.findById(book.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(book.getAuthorId()));

        Book toSave = new Book(book.getName(), book.getCategory(), author, book.getAvailableCopies());
        bookRepository.save(toSave);
        return Optional.of(toSave);
    }

    @Override
    public Optional<Book> takeBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        Integer currentAvailableCopies = book.getAvailableCopies();
        if(currentAvailableCopies <= 0)
            throw new BookNotAvailable(bookId);

        book.setAvailableCopies(currentAvailableCopies - 1);
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository .findById(id);
    }
}
