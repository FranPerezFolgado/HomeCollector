package com.fpf.homecollector.book_microservice.domain.repository;

import com.fpf.homecollector.book_microservice.domain.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository {

    Optional<Book> findById(UUID id);

    List<Book> findAll();

    void save(Book book);

}
