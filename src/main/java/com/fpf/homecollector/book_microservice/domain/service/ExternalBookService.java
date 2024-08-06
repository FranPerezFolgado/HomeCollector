package com.fpf.homecollector.book_microservice.domain.service;

import com.fpf.homecollector.book_microservice.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExternalBookService {

    List<Book> searchBooks(String filter);
}
