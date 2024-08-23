package com.fpf.homecollector.book_microservice.infrastructure.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.service.ExternalBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GoogleBookApiService implements ExternalBookService {

    private final static String API_URL = "https://www.googleapis.com/books/v1";//volumes?q=";
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Value("${books.api.key}")
    private String API_KEY;

    public GoogleBookApiService() {
        this.webClient = WebClient.builder().baseUrl(API_URL).build();
        this.objectMapper = new ObjectMapper();
    }

    private static Book mapSingleBook(JsonNode item) {
        JsonNode volumeInfo = item.path("volumeInfo");
        String title = volumeInfo.path("title").asText();
        String isbn = volumeInfo.path("industryIdentifiers").get(0).path("identifier").asText();
        String author = volumeInfo.path("authors").get(0).asText();
        String publisher = volumeInfo.path("publisher").asText();
        String imageLink = volumeInfo.path("imageLinks").path("thumbnail").asText();
        String externalId = item.path("id").asText();

        return new Book(isbn, title, author, null, false, null, publisher, imageLink, externalId);
    }

    @Override
    public List<Book> searchBooks(String filter) {
        log.info("Searching book with filter: {}", filter);
        String booksFound = webClient.get().uri("/volumes?q=" + filter + "&key=" + API_KEY).retrieve().bodyToMono(String.class).block();
        log.info("Books found : {}", booksFound);
        return mapToBooks(booksFound);
    }

    private List<Book> mapToBooks(String booksFound) {
        final List<Book> books = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(booksFound);
            JsonNode items = root.get("items");
            for (JsonNode item : items) {
                books.add(mapSingleBook(item));
            }
        } catch (Exception e) {
            log.error("Error mapping books", e);
        }

        return books;
    }
}
