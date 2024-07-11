package com.fpf.homecollector.book_microservice.domain.repository;

import com.fpf.homecollector.book_microservice.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository {

    void save(Book book);

    List<Book> findAll();

    Optional<Book> findById(UUID id);

    List<Book> findByAuthor(String author);
    
}
