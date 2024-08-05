// `src/test/java/com/fpf/homecollector/book_microservice/application/service/BookSearchServiceTest.java`
package com.fpf.homecollector.book_microservice.application.service;

import com.fpf.homecollector.book_microservice.application.request.SearchBookRequest;
import com.fpf.homecollector.book_microservice.application.response.SearchBookResponse;
import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.service.ExternalBookService;
import com.fpf.homecollector.book_microservice.utils.BookUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

class BookSearchServiceTest {

    @Mock
    private ExternalBookService externalBookService;

    @InjectMocks
    private BookSearchService bookSearchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchBook() {
        // Given
        SearchBookRequest request = new SearchBookRequest("title", "author", "publisher", "isbn");
        SearchBookResponse response = new SearchBookResponse("title", "author", "publisher", "isbn", "thumbnail");
        Book expectedBook = BookUtils.createBook();
        doReturn(List.of(expectedBook)).when(externalBookService).searchBook("intitle:title+inauthor:author+inpublisher:publisher+isbn:isbn");

        // When
        List<SearchBookResponse> result = bookSearchService.searchBook(request);

        // Then
        assertEquals(1, result.size());
        assertEquals(expectedBook.getTitle(), result.getFirst().title());
        assertEquals(expectedBook.getAuthor(), result.getFirst().author());
        assertEquals(expectedBook.getPublisher(), result.getFirst().publisher());
        assertEquals(expectedBook.getIsbn(), result.getFirst().isbn());
        assertEquals(expectedBook.getImageLink(), result.getFirst().imageLink());
    }
}