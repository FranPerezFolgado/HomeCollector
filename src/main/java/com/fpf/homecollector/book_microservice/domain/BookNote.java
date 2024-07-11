package com.fpf.homecollector.book_microservice.domain;

import lombok.Getter;

import java.util.UUID;


@Getter
public class BookNote {

    private UUID id;
    private String note;
    private int page;
    private String quote;

    public BookNote(String note, int page, String quote) {
        this.id = UUID.randomUUID();
        this.note = note;
        this.page = page;
        this.quote = quote;
    }

    public BookNote() {

    }
}
