package com.fpf.homecollector.book_microservice.application.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record FindBookResponse(
        UUID id,
        String name,
        String isbn,
        String author,
        BigDecimal price,
        List<FindBookNoteResponse> notes
) {


}
