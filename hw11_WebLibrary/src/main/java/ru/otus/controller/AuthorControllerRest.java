package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dao.AuthorRepository;
import ru.otus.domain.Author;

@RestController
@RequiredArgsConstructor
public class AuthorControllerRest {
    private final AuthorRepository authorRepository;

    @GetMapping("/api/authors")
    public Flux<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @DeleteMapping("/api/authors/{id}")
    public Mono<Void> deleteAuthor(@PathVariable String id) {
        return authorRepository.deleteById(id);
    }
}
