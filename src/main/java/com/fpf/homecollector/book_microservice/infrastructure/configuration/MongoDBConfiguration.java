package com.fpf.homecollector.book_microservice.infrastructure.configuration;

import com.fpf.homecollector.book_microservice.infrastructure.repository.SpringMongoBookRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = SpringMongoBookRepository.class)
public class MongoDBConfiguration {
}
