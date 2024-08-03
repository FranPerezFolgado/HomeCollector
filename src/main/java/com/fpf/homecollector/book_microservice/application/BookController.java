package com.fpf.homecollector.book_microservice.application;

import com.fpf.homecollector.book_microservice.application.request.AddNoteRequest;
import com.fpf.homecollector.book_microservice.application.request.AddRatingRequest;
import com.fpf.homecollector.book_microservice.application.request.CreateBookRequest;
import com.fpf.homecollector.book_microservice.application.response.BookMapperUtils;
import com.fpf.homecollector.book_microservice.application.response.CreateBookResponse;
import com.fpf.homecollector.book_microservice.application.response.FindBookResponse;
import com.fpf.homecollector.book_microservice.domain.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books/v1")
@Slf4j
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //TODO
    //PAGINATION
    @GetMapping
    public ResponseEntity<List<FindBookResponse>> findBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(BookMapperUtils.mapFindBooks(bookService.findBooks()));
    }

    @GetMapping("/author")
    public ResponseEntity<List<FindBookResponse>> findBooksByAuthor(@RequestParam("author") String author) {
        return ResponseEntity.status(HttpStatus.OK).body(BookMapperUtils.mapFindBooks(bookService.findBooksByAuthor(author)));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<FindBookResponse> findBook(@PathVariable("bookId") UUID bookId) {
        return ResponseEntity.status(HttpStatus.OK).body(BookMapperUtils.mapFindBook(bookService.findBook(bookId)));
    }


    @PostMapping
    public ResponseEntity<CreateBookResponse> createBook(@RequestBody CreateBookRequest request) {
        UUID id = bookService.saveBook(request.mapToEntity());
        log.debug("Book created: {}", id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateBookResponse(id));
    }

    @PostMapping("/{bookId}/note")
    public ResponseEntity<Void> addNote(@PathVariable("bookId") UUID bookId, @RequestBody AddNoteRequest request) {
        bookService.addNote(bookId, request.mapToEntity());
        log.debug("Note added to book: {}", bookId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{bookId}/{noteId}")
    public ResponseEntity<Void> addNote(@PathVariable("bookId") UUID bookId, @PathVariable("noteId") UUID noteId) {
        bookService.deleteNote(bookId, noteId);
        log.debug("Note {} deleted from book: {}", noteId, bookId);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/{bookId}/rating")
    public ResponseEntity<Void> addRating(@PathVariable("bookId") UUID bookId, @RequestBody AddRatingRequest request) {
        bookService.addRating(bookId, request.mapToEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{bookId}/rating")
    public ResponseEntity<Void> deleteRating(@PathVariable("bookId") UUID bookId) {
        bookService.deleteRating(bookId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
