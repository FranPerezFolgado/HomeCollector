package com.fpf.homecollector.book_microservice.application.response;

import com.fpf.homecollector.book_microservice.domain.Book;

public record SearchBookResponse(
        String title,
        String author,
        String publisher,
        String isbn,
        String imageLink
) {

    public SearchBookResponse(Book book) {
        this(book.getTitle(), book.getAuthor(), book.getPublisher(), book.getIsbn(), book.getImageLink());

    }


}
