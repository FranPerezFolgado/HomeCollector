package com.fpf.homecollector.book_microservice.application;

import com.fpf.homecollector.book_microservice.application.request.AddNoteRequest;
import com.fpf.homecollector.book_microservice.application.request.CreateBookRequest;
import com.fpf.homecollector.book_microservice.application.request.DeleteNoteRequest;
import com.fpf.homecollector.book_microservice.application.response.BookMapperUtils;
import com.fpf.homecollector.book_microservice.application.response.CreateBookResponse;
import com.fpf.homecollector.book_microservice.application.response.FindBookResponse;
import com.fpf.homecollector.book_microservice.domain.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    public FindBookResponse findBook(@PathVariable("bookId") UUID bookId) {
        return BookMapperUtils.mapFindBook(bookService.findBook(bookId));
    }

    //TODO
    //PAGINATION
    @GetMapping()
    public List<FindBookResponse> findBooks(@PathVariable("bookId") UUID bookId) {
        return BookMapperUtils.mapFindBooks(bookService.findBooks());
    }

    @PostMapping
    public CreateBookResponse createBook(@RequestBody CreateBookRequest request) {
        UUID id = bookService.saveBook(request.mapToEntity());
        log.debug("Book created: {}", id);
        return new CreateBookResponse(id);
    }

    @PostMapping
    public void addNote(@RequestBody AddNoteRequest request) {
        bookService.addNote(request.bookId(), request.mapToEntity());
        log.debug("Note added to book: {}", request.bookId());
    }

    @DeleteMapping
    public void addNote(@RequestBody DeleteNoteRequest request) {
        bookService.deleteNote(request.bookId(), request.noteId());
        log.debug("Note {} deleted from book: {}", request.noteId(), request.bookId());
    }

}
