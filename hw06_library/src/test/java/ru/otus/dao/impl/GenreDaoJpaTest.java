package ru.otus.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Genre;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("дао жанров должно ")
@DataJpaTest
@Import(GenreDaoJpa.class)
class GenreDaoJpaTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private GenreDaoJpa genreDaoJpa;

    private static final long EXPECTED_COUNT_OF_GENRES = 1;
    private static final long EXISTING_GENRE_ID = 1;
    private static final String EXISTING_GENRE_NAME = "Fantasy";

    @Test
    @DisplayName("правильно возвращать количество жанров")
    void shouldReturnCorrectCountOfGenre() {
        long actualCount = genreDaoJpa.count();
        assertThat(actualCount).isEqualTo(EXPECTED_COUNT_OF_GENRES);
    }

    @Test
    @DisplayName("правильно возвращать жанр по id")
    void shouldReturnCorrectGenreById() {
        Genre expectedGenre = testEntityManager.find(Genre.class, EXISTING_GENRE_ID);
        Genre actualGenre = genreDaoJpa.getGenreById(EXISTING_GENRE_ID);
        assertThat(actualGenre).isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("правильно возвращать список жанров")
    void shouldReturnCorrectListOfGenres() {
        List<Genre> expectedGenreList = List.of(testEntityManager.find(Genre.class, EXISTING_GENRE_ID));
        List<Genre> actualGenreList = genreDaoJpa.getAllGenre();
        assertThat(actualGenreList).isEqualTo(expectedGenreList);
    }

    @Test
    @DisplayName("добавлять жанр")
    void shouldInsertGenre() {
        Genre expectedGenre = new Genre("test");
        genreDaoJpa.insertGenre(expectedGenre);
        Genre actualGenre = genreDaoJpa.getGenreById(2);
        assertThat(actualGenre).isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("удалять жанр")
    void shouldDeleteGenre() {
        Genre genre = testEntityManager.find(Genre.class, EXISTING_GENRE_ID);
        assertThat(genre).isNotNull();
        testEntityManager.detach(genre);
        genreDaoJpa.deleteGenreById(EXISTING_GENRE_ID);
        Genre deletedGenre = testEntityManager.find(Genre.class, EXISTING_GENRE_ID);
        assertThat(deletedGenre).isNull();
    }

    @Test
    @DisplayName("обновлять название жанра")
    void shouldCorrectUpdateGenreNameByID() {
        Genre oldGenre = testEntityManager.find(Genre.class, EXISTING_GENRE_ID);
        String oldName = oldGenre.getName();
        assertThat(oldName).isEqualTo(EXISTING_GENRE_NAME);
        genreDaoJpa.updateGenreNameById(EXISTING_GENRE_ID, "horror");
        testEntityManager.detach(oldGenre);
        String newName = testEntityManager.find(Genre.class, EXISTING_GENRE_ID).getName();
        assertThat(newName).isEqualTo("horror");
    }
}