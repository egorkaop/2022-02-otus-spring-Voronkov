package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Genre;

@RestController
@RequiredArgsConstructor
public class GenreControllerRest {
    private final GenreRepository genreRepository;

    @GetMapping("/api/genres")
    public Flux<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @DeleteMapping("/api/genres/{id}")
    public Mono<Void> deleteGenre(@PathVariable String id) {
        return genreRepository.deleteById(id);
    }
}
