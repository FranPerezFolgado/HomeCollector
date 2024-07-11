package com.fpf.homecollector.book_microservice.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Book {

    private String name;
    private String isbn;
    private String author;
    private BigDecimal price; //maybe to calculate total spent
    private List<BookNote> notes;
    private UUID id;
    /*TODO
        - author document?
     */


    public Book() {

    }

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
