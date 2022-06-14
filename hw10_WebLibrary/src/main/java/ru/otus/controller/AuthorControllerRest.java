package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dto.AuthorDto;
import ru.otus.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorControllerRest {
    private final AuthorService authorService;

    @GetMapping("/api/authors")
    public List<AuthorDto> getAuthors() {
        return authorService.getAllAuthors();
    }

    @DeleteMapping("/api/authors/{id}")
    public void deleteGenre(@PathVariable long id) {
        authorService.deleteAuthorById(id);
    }
}
