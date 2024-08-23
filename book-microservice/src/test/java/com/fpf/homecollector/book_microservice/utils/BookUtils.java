package com.fpf.homecollector.book_microservice.utils;

import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.BookNote;
import com.fpf.homecollector.book_microservice.domain.BookRating;

import java.math.BigDecimal;

public class BookUtils {


    public static Book createBook() {
        return new Book("testBook", "testisbn", "testAuthor", BigDecimal.ONE, false, createRating(), "testPublisher", "testImageLink", "1");
    }

    public static BookNote createNote() {
        return new BookNote("testNote", 1, "testQuote");
    }

    public static Book createBookWithOneNote() {
        Book book = createBook();
        BookNote note = createNote();
        book.addNote(note);
        return book;
    }

    public static Book createBookWithRating() {
        Book bookRated = new Book("testBook", "testisbn", "testAuthor", BigDecimal.ONE);
        bookRated.setRating(createRating());
        return bookRated;
    }

    public static BookRating createRating() {
        return new BookRating("review", 1);
    }
}
