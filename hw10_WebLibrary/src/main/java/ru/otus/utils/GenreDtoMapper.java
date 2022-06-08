package ru.otus.utils;

import ru.otus.domain.Genre;
import ru.otus.dto.GenreDto;

import java.util.List;

public interface GenreDtoMapper {
    GenreDto convertGenreToDto(Genre genre);

    List<GenreDto> convertListGenresToDto(List<Genre> genreList);
}
