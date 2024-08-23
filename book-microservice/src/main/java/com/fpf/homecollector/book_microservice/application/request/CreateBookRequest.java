package com.fpf.homecollector.book_microservice.application.request;

import com.fpf.homecollector.book_microservice.domain.Book;

import java.math.BigDecimal;

public record CreateBookRequest(
        String name,
        String isbn,
        String author,
        BigDecimal price
) {


    public Book mapToEntity() {
        return new Book(
                this.name,
                this.isbn,
                this.author,
                this.price
        );
    }
}
