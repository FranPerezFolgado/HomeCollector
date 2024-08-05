package com.fpf.homecollector.book_microservice.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Book {

    private String title;
    private String isbn;
    private String author;
    private BigDecimal price; //maybe to calculate total spent
    private List<BookNote> notes = new ArrayList<>();
    private boolean finished;
    @Setter
    private BookRating rating;
    private UUID id;
    /*TODO
        - author document?
     */


    public Book() {

    }

    public Book(String isbn, String title, String author, BigDecimal price) {
        this.id = UUID.randomUUID();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        this.finished = false;
    }

    public Book(String title, String isbn, String author, BigDecimal price, boolean finished, BookRating rating) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.price = price;
        this.finished = finished;
        this.rating = rating;
    }


    public void addNote(BookNote note) {
        this.notes.add(note);
    }

    public void removeNote(UUID uuid) {
        this.notes.stream()
                .filter(note -> note.getId().equals(uuid))
                .findFirst().ifPresent(this.notes::remove);
    }


    public void removeRating() {
        this.rating = null;
    }
}
