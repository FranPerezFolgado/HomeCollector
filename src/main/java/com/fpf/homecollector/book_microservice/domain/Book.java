package com.fpf.homecollector.book_microservice.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Book {
    private final UUID id;
    private final String name;
    private final String isbn;
    private final String author;
    private final BigDecimal price; //maybe to calculate total spent
    private final List<BookNote> notes;
    /*TODO
        - author document?
     */


    public Book(String name, String isbn, String author, BigDecimal price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.isbn = isbn;
        this.author = author;
        this.price = price;
        this.notes = new ArrayList<>();
    }

    public void addNote(BookNote note) {
        this.notes.add(note);
    }

    public void removeNote(UUID uuid) {
        this.notes.stream()
                .filter(note -> note.getId().equals(uuid))
                .findFirst().ifPresent(this.notes::remove);
    }

}
