package com.fpf.homecollector.book_microservice.domain;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository {

    Optional<Book> findById(UUID id);

    void save(Book book);

}
