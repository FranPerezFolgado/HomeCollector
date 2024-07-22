package com.fpf.homecollector.book_microservice.domain.service;

import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.BookNote;
import com.fpf.homecollector.book_microservice.domain.BookRating;
import com.fpf.homecollector.book_microservice.domain.repository.BookRepository;
import com.fpf.homecollector.book_microservice.infrastructure.service.BookServiceImpl;
import com.fpf.homecollector.book_microservice.utils.BookUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class BookServiceImplTest {

    private BookRepository bookRepository;
    private BookServiceImpl bookService;

    @BeforeEach
    void setup() {
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void saveBook() {
        final Book book = BookUtils.createBookWithOneNote();
        final UUID uuid = bookService.saveBook(book);

        verify(bookRepository, times(1)).save(any(Book.class));
        assertNotNull(uuid);

    }

    @Test
    void addNote() {
        final Book book = BookUtils.createBook();
        final BookNote note = BookUtils.createNote();

        mockFindBook(book);
        bookService.addNote(book.getId(), note);

        verify(bookRepository, times(1)).findById(any(UUID.class));
        verify(bookRepository, times(1)).save(argThat(
                bookToSave -> 1 == bookToSave.getNotes().size()
        ));

    }

    @Test
    void deleteNote() {
        final Book book = BookUtils.createBookWithOneNote();

        mockFindBook(book);
        bookService.deleteNote(book.getId(), book.getNotes().getFirst().getId());

        verify(bookRepository, times(1)).findById(any(UUID.class));
        verify(bookRepository, times(1)).save(argThat(
                bookToSave -> bookToSave.getNotes().isEmpty()
        ));
    }


    @Test
    void addRating() {
        Book book = BookUtils.createBook();
        mockFindBook(book);
        BookRating rating = BookUtils.createRating();
        bookService.addRating(book.getId(), rating);

        assertNotNull(book.getRating());

    }

    @Test
    void deleteRating() {
        Book book = BookUtils.createBookWithRating();
        mockFindBook(book);
        bookService.deleteRating(book.getId());

        assertNull(book.getRating());

    }

    private void mockFindBook(Book book) {
        doReturn(Optional.of(book)).when(bookRepository).findById(any(UUID.class));
    }
}