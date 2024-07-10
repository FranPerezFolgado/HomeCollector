package com.fpf.homecollector.book_microservice.domain.service;

import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.BookNote;

import java.util.UUID;

public interface BookService {

    UUID saveBook(Book book);

    void addNote(UUID bookUuid, BookNote note);

    void deleteNote(UUID bookUuid, UUID noteUuid);
}
