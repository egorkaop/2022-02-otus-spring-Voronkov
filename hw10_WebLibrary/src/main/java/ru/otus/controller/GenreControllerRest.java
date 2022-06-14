package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dto.GenreDto;
import ru.otus.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreControllerRest {
    private final GenreService genreService;

    @GetMapping("/api/genres")
    public List<GenreDto> getGenres() {
        return genreService.getAllGenres();
    }

    @DeleteMapping("/api/genres/{id}")
    public void deleteGenre(@PathVariable long id) {
        genreService.deleteGenreById(id);
    }
}
