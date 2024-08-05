package com.fpf.homecollector.book_microservice.infrastructure.service;

import com.fpf.homecollector.book_microservice.domain.Book;
import com.fpf.homecollector.book_microservice.domain.service.ExternalBookService;

import java.util.List;

public class GoogleBookApiService implements ExternalBookService {

    private final static String API_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private final static String API_KEY = "AIzaSyCtsS05xy8gE4SpFFpscxWJ6r1z5NsedIQ";

    @Override
    public List<Book> searchBook(String filter) {


        return null;
    }
}
