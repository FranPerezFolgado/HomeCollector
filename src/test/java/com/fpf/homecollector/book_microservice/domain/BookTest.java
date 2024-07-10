package com.fpf.homecollector.book_microservice.domain;

import com.fpf.homecollector.book_microservice.utils.BookUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {


    @Test
    void addNote() {
        Book testBook = BookUtils.createBookWithOneNote();
        assertEquals(1, testBook.getNotes().size());

    }

    @Test
    void removeNote() {
        Book testBook = BookUtils.createBookWithOneNote();

        testBook.removeNote(testBook.getNotes().getFirst().getId());

        assertEquals(0, testBook.getNotes().size());
    }
}