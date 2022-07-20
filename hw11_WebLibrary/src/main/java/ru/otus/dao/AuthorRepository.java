package ru.otus.dao;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;

import java.util.List;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    Flux<Author> findAll();

    Mono<Void> deleteById(String id);

    @Override
    Flux<Author> findAllById(Iterable<String> strings);

    Flux<Author> findAllByNameIn(List<String> authorsName);

}
