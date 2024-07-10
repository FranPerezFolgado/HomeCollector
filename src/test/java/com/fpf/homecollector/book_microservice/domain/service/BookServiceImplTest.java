package com.fpf.homecollector.book_microservice.domain.service;

import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.BookNote;
import com.fpf.homecollector.book_microservice.domain.BookRepository;
import com.fpf.homecollector.book_microservice.utils.BookUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

        doReturn(Optional.of(book)).when(bookRepository).findById(any(UUID.class));
        bookService.addNote(book.getId(), note);

        verify(bookRepository, times(1)).findById(any(UUID.class));
        verify(bookRepository, times(1)).save(argThat(
                bookToSave -> 1 == bookToSave.getNotes().size()
        ));

    }

    @Test
    void deleteNote() {
        final Book book = BookUtils.createBookWithOneNote();

        doReturn(Optional.of(book)).when(bookRepository).findById(any(UUID.class));
        bookService.deleteNote(book.getId(), book.getNotes().getFirst().getId());

        verify(bookRepository, times(1)).findById(any(UUID.class));
        verify(bookRepository, times(1)).save(argThat(
                bookToSave -> bookToSave.getNotes().isEmpty()
        ));
    }
}