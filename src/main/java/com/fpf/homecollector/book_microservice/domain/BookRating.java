package com.fpf.homecollector.book_microservice.domain;

import lombok.Getter;


@Getter
public class BookRating {

    private String review;
    private int rating;

    public BookRating(String review, int rating) {
        if (rating > 5 || rating < 0) {
            throw new RuntimeException("Book rating must be between 0 and 5");
        }
        this.rating = rating;
        this.review = review;
    }

    public BookRating() {

    }
}
