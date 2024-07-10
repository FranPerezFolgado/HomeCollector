package com.fpf.homecollector.book_microservice.application.response;

import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.BookNote;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapperUtils {

    public static List<FindBookResponse> mapFindBooks(List<Book> books) {
        return books.stream().map(
                        BookMapperUtils::mapFindBook)
                .collect(Collectors.toList());

    }

    public static FindBookResponse mapFindBook(Book book) {
        return new FindBookResponse(
                book.getId(),
                book.getName(),
                book.getIsbn(),
                book.getAuthor(),
                book.getPrice(),
                mapFindNotes(book.getNotes())
        );

    }

    public static List<FindBookNoteResponse> mapFindNotes(List<BookNote> notes) {
        return notes.stream().map(
                        BookMapperUtils::mapFindNote)
                .collect(Collectors.toList());

    }

    public static FindBookNoteResponse mapFindNote(BookNote note) {
        return new FindBookNoteResponse(
                note.getId(),
                note.getNote(),
                note.getPage(),
                note.getQuote()
        );
    }
}
