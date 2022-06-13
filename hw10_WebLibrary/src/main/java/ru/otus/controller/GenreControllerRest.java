package ru.otus.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dto.GenreDto;
import ru.otus.service.GenreService;

@RestController
@RequiredArgsConstructor
public class GenreControllerRest {
    private final GenreService genreService;

    @GetMapping("/api/genres")
    public List<GenreDto> getGenres(){
        return genreService.getAllGenres();
    }
}
