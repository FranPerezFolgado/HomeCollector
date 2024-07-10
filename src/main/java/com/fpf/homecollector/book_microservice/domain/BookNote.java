package com.fpf.homecollector.book_microservice.domain;

import lombok.Getter;

import java.util.UUID;


@Getter
public class BookNote {

    private final UUID id;
    private final String note;
    private final int page;
    private final String quote;

    public BookNote(UUID id, String note, int page, String quote) {
        this.id = id;
        this.note = note;
        this.page = page;
        this.quote = quote;
    }
}
