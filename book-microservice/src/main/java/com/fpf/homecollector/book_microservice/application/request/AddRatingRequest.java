package com.fpf.homecollector.book_microservice.application.request;

import com.fpf.homecollector.book_microservice.domain.BookRating;

public record AddRatingRequest(
        String review,
        int rating) {


    public BookRating mapToEntity() {
        return new BookRating(review, rating);
    }
}
