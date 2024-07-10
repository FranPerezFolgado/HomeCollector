package com.fpf.homecollector.book_microservice.application.response;

import java.util.UUID;

public record FindBookNoteResponse(
        UUID id,
        String note,
        int page,
        String quote
) {
}
