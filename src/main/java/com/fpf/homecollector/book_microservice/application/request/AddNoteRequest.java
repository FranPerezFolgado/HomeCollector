package com.fpf.homecollector.book_microservice.application.request;

import com.fpf.homecollector.book_microservice.domain.BookNote;

import java.util.UUID;

public record AddNoteRequest(
        UUID bookId,
        String note,
        int page,
        String quote
) {


    public BookNote mapToEntity() {
        return new BookNote(
                this.note,
                this.page,
                this.quote
        );
    }
}
