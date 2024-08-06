package com.fpf.homecollector.book_microservice.application;

import com.fpf.homecollector.book_microservice.application.request.SearchBookRequest;
import com.fpf.homecollector.book_microservice.application.response.SearchBookResponse;
import com.fpf.homecollector.book_microservice.application.service.BookSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books/v1/searcher")
@Slf4j
public class BookSearcherController {

    private final BookSearchService bookSearchService;

    public BookSearcherController(BookSearchService bookSearchService) {
        this.bookSearchService = bookSearchService;
    }

    //TODO
    //PAGINATION
    @GetMapping
    public ResponseEntity<List<SearchBookResponse>> findBooks(@RequestBody SearchBookRequest searchBookRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(bookSearchService.searchBook(searchBookRequest));
    }


}
