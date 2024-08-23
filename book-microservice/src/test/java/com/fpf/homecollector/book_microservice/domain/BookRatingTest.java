package com.fpf.homecollector.book_microservice.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookRatingTest {


    @Test
    void verifyValidRatingThrowsException() {

        assertThrows(RuntimeException.class, () -> new BookRating("test", 10));

    }

    @Test
    void verifyValidRating() {

        assertDoesNotThrow(() -> new BookRating("test", 5));

    }


}