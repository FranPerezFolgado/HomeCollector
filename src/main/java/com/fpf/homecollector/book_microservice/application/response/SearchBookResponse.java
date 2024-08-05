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

    public SearchBookResponse(String title, String author, String publisher, String isbn, String imageLink) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.imageLink = imageLink;
    }
}
