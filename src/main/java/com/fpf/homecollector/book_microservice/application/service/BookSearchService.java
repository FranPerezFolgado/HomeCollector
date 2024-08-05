package com.fpf.homecollector.book_microservice.application.service;

import com.fpf.homecollector.book_microservice.application.request.SearchBookRequest;
import com.fpf.homecollector.book_microservice.application.response.SearchBookResponse;
import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.service.ExternalBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;

@Service
@Slf4j
public class BookSearchService {

    private final ExternalBookService externalBookService;

    public BookSearchService(ExternalBookService externalBookService) {
        this.externalBookService = externalBookService;
    }

    public List<SearchBookResponse> searchBook(SearchBookRequest searchBookRequest) {

        String query = buildQuery(searchBookRequest);
        List<Book> foundBooks = externalBookService.searchBook(query);
        return mapListToResponse(foundBooks);

    }

    private List<SearchBookResponse> mapListToResponse(List<Book> books) {
        return books.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SearchBookResponse mapToResponse(Book book) {
        return new SearchBookResponse(book);
    }

    private String buildQuery(SearchBookRequest bookSearchDto) {
        StringJoiner query = new StringJoiner("+");

        if (bookSearchDto.title() != null && !bookSearchDto.title().isEmpty()) {
            query.add("intitle:" + bookSearchDto.title());
        }
        if (bookSearchDto.author() != null && !bookSearchDto.author().isEmpty()) {
            query.add("inauthor:" + bookSearchDto.author());
        }
        if (bookSearchDto.publisher() != null && !bookSearchDto.publisher().isEmpty()) {
            query.add("inpublisher:" + bookSearchDto.publisher());
        }
        if (bookSearchDto.isbn() != null && !bookSearchDto.isbn().isEmpty()) {
            query.add("isbn:" + bookSearchDto.isbn());
        }

        return query.toString();
    }
}
