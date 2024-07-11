package com.fpf.homecollector.book_microservice.domain.service;

import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.BookNote;

import java.util.List;
import java.util.UUID;

public interface BookService {

    UUID saveBook(final Book book);

    Book findBook(final UUID bookId);

    List<Book> findBooks();

    List<Book> findBooksByAuthor(final String author);

    void addNote(final UUID bookUuid, final BookNote note);

    void deleteNote(final UUID bookUuid, final UUID noteUuid);
}
