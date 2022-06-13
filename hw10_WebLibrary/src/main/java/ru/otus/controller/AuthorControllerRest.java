package ru.otus.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dto.AuthorDto;
import ru.otus.service.AuthorService;

@RestController
@RequiredArgsConstructor
public class AuthorControllerRest {
    private final AuthorService authorService;

    @GetMapping("/api/authors")
    public List<AuthorDto> getAuthors(){
        return authorService.getAllAuthors();
    }
}
