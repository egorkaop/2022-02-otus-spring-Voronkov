package ru.otus.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.dto.GenreDto;
import ru.otus.service.GenreService;

@Controller
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/genreList")
    public String listGenre(Model model){
        List<GenreDto> genreDtoList = genreService.getAllGenres();
        model.addAttribute("genres",genreDtoList);
        return "genreList";
    }

    @GetMapping("/genreDelete")
    public String deleteGenre(@RequestParam("id") long id){
        genreService.deleteGenreById(id);
        return "redirect:/genreList";
    }
}
