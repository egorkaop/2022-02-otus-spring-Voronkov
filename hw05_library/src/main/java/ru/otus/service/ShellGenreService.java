package ru.otus.service;

import ru.otus.domain.Genre;

import java.util.List;

public interface ShellGenreService {
    void getGenreCount();

    void getGenreByID();

    void getAllGenres();

    void insertGenre();

    void deleteGenreById();
}
