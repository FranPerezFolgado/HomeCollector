package com.fpf.homecollector.book_microservice.application;

import com.fpf.homecollector.book_microservice.application.request.AddNoteRequest;
import com.fpf.homecollector.book_microservice.application.request.AddRatingRequest;
import com.fpf.homecollector.book_microservice.application.request.CreateBookRequest;
import com.fpf.homecollector.book_microservice.application.response.BookMapperUtils;
import com.fpf.homecollector.book_microservice.application.response.CreateBookResponse;
import com.fpf.homecollector.book_microservice.application.response.FindBookResponse;
import com.fpf.homecollector.book_microservice.domain.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //TODO
    //PAGINATION
    @GetMapping
    public List<FindBookResponse> findBooks() {
        return BookMapperUtils.mapFindBooks(bookService.findBooks());
    }

    @GetMapping("/author")
    public List<FindBookResponse> findBooksByAuthor(@RequestParam("author") String author) {
        return BookMapperUtils.mapFindBooks(bookService.findBooksByAuthor(author));
    }

    @GetMapping("/{bookId}")
    public FindBookResponse findBook(@PathVariable("bookId") UUID bookId) {
        return BookMapperUtils.mapFindBook(bookService.findBook(bookId));
    }


    @PostMapping
    public CreateBookResponse createBook(@RequestBody CreateBookRequest request) {
        UUID id = bookService.saveBook(request.mapToEntity());
        log.debug("Book created: {}", id);
        return new CreateBookResponse(id);
    }

    @PostMapping("/{bookId}/note")
    public void addNote(@PathVariable("bookId") UUID bookId, @RequestBody AddNoteRequest request) {
        bookService.addNote(bookId, request.mapToEntity());
        log.debug("Note added to book: {}", bookId);
    }

    @DeleteMapping("/{bookId}/{noteId}")
    public void addNote(@PathVariable("bookId") UUID bookId, @PathVariable("noteId") UUID noteId) {
        bookService.deleteNote(bookId, noteId);
        log.debug("Note {} deleted from book: {}", noteId, bookId);
    }

    @PostMapping("/{bookId}/rating")
    public void addRating(@PathVariable("bookId") UUID bookId, @RequestBody AddRatingRequest request) {
        bookService.addRating(bookId, request.mapToEntity());
    }

    @DeleteMapping("/{bookId}/rating")
    public void deleteRating(@PathVariable("bookId") UUID bookId) {
        bookService.deleteRating(bookId);
    }

}
