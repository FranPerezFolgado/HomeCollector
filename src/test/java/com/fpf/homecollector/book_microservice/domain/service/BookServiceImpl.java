package com.fpf.homecollector.book_microservice.domain.service;

import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.BookNote;
import com.fpf.homecollector.book_microservice.domain.BookRepository;

import java.util.UUID;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public UUID saveBook(Book book) {
        bookRepository.save(book);
        return book.getId();
    }

    @Override
    public void addNote(UUID bookUuid, BookNote note) {
        Book book = getBook(bookUuid);
        book.addNote(note);
        bookRepository.save(book);
    }

    @Override
    public void deleteNote(UUID bookUuid, UUID noteUuid) {
        Book book = getBook(bookUuid);
        book.removeNote(noteUuid);
        bookRepository.save(book);
    }

    private Book getBook(UUID id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Book with given id does not exist " + id));
    }
}
