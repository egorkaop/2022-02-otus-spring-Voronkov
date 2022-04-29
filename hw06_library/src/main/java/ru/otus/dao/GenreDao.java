package ru.otus.dao;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreDao {
    long count();

    Genre getGenreById(long id);

    List<Genre> getAllGenre();

    void insertGenre(Genre genre);

    void deleteGenreById(long id);

    void updateGenreNameById(long id,String name);
}
