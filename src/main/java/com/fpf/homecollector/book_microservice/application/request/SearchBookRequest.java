package com.fpf.homecollector.book_microservice.application.request;

public record SearchBookRequest(String title,
                                String author,
                                String publisher,
                                String isbn) {
}
