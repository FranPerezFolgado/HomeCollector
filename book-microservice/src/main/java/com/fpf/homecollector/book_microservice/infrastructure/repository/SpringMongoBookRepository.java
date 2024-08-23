package com.fpf.homecollector.book_microservice.infrastructure.repository;

import com.fpf.homecollector.book_microservice.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringMongoBookRepository extends MongoRepository<Book, UUID> {

    List<Book> findByAuthor(String author);
}
