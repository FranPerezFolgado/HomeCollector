package com.fpf.homecollector.book_microservice.application.request;

import java.util.UUID;

public record DeleteNoteRequest(
        UUID noteId
) {

}
