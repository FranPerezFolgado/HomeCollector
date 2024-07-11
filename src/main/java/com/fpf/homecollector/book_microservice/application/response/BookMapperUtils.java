package com.fpf.homecollector.book_microservice.application.response;

import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.BookNote;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class BookMapperUtils {

    public static List<FindBookResponse> mapFindBooks(List<Book> books) {
        return books.stream().map(
                        BookMapperUtils::mapFindBook)
                .toList();

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
                .toList();

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
