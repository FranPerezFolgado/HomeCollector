package com.fpf.homecollector.book_microservice.application.service;

import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.BookNote;
import com.fpf.homecollector.book_microservice.domain.BookRating;
import com.fpf.homecollector.book_microservice.domain.repository.BookRepository;
import com.fpf.homecollector.book_microservice.domain.service.BookService;
import com.fpf.homecollector.book_microservice.domain.service.ExternalBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final ExternalBookService bookApiService;

    public BookServiceImpl(BookRepository bookRepository, ExternalBookService bookApiService) {
        this.bookRepository = bookRepository;
        this.bookApiService = bookApiService;
    }

    @Override
    public UUID saveBook(Book book) {
        bookRepository.save(book);
        return book.getId();
    }

    @Override
    public Book findBook(UUID bookId) {
        return getBook(bookId);
    }

    @Override
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
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

    @Override
    public void addRating(UUID bookUuid, BookRating rating) {
        Book book = getBook(bookUuid);
        book.setRating(rating);

    }

    @Override
    public void deleteRating(UUID bookUuid) {
        Book book = getBook(bookUuid);
        book.removeRating();
    }

    private Book getBook(UUID id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Book with given id does not exist " + id));
    }
}
