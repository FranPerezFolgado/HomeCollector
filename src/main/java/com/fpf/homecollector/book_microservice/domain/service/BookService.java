package com.fpf.homecollector.book_microservice.domain.service;

import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.BookNote;

import java.util.List;
import java.util.UUID;

public interface BookService {

    Book findBook(UUID bookId);

    List<Book> findBooks();

    UUID saveBook(Book book);

    void addNote(UUID bookUuid, BookNote note);

    void deleteNote(UUID bookUuid, UUID noteUuid);
}
