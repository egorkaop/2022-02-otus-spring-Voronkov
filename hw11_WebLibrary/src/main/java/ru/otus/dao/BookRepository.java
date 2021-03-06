package ru.otus.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findAll();

    Mono<Void> deleteById(String id);

    Mono<Book> findById(String id);

}
