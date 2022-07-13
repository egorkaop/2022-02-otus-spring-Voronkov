package ru.otus.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
    Flux<Genre> findAll();

    Mono<Void> deleteById(String id);
}
