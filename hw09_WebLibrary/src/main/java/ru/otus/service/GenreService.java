package ru.otus.service;

import ru.otus.dto.GenreDto;

import java.util.List;

public interface GenreService {
    long getGenreCount();

    GenreDto getGenreByID(long id);

    List<GenreDto> getAllGenres();

    void insertGenre();

    void deleteGenreById();

    void updateGenreNameById();

    List<GenreDto> getAllGenresByBookId();
}
