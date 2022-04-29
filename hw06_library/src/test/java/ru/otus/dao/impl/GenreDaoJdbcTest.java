//package ru.otus.dao.impl;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.dao.EmptyResultDataAccessException;
//import ru.otus.domain.Genre;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@DisplayName("Dao для работы с жанрами должно")
//@JdbcTest
//@Import(GenreDaoJdbc.class)
//class GenreDaoJdbcTest {
//    private static final int EXPECTED_GENRES_COUNT = 1;
//    private static final int EXISTING_GENRE_ID = 1;
//    private static final String EXISTING_GENRE_TITLE = "Fantasy";
//    @Autowired
//    private GenreDaoJdbc genreDaoJdbc;
//
//    @DisplayName("правильно возвращать количество записей")
//    @Test
//    void shouldReturnCorrectCountOfGenre() {
//        int actualGenresCount = genreDaoJdbc.count();
//        assertThat(actualGenresCount).isEqualTo(EXPECTED_GENRES_COUNT);
//    }
//
//    @DisplayName("правильно возвращать жанр по id")
//    @Test
//    void shouldReturnCorrectGenreById() {
//        Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_TITLE);
//        Genre actualGenre = genreDaoJdbc.getGenreById(EXISTING_GENRE_ID);
//        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
//    }
//
//    @DisplayName("правильно возвращать список с жанрами")
//    @Test
//    void shouldReturnCorrectGenreList() {
//        List<Genre> expectedGenreList = List.of(new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_TITLE));
//        List<Genre> actualGenreList = genreDaoJdbc.getAllGenre();
//        assertThat(actualGenreList).usingRecursiveComparison().isEqualTo(expectedGenreList);
//    }
//
//    @DisplayName("добавлять жанр")
//    @Test
//    void shouldInsertGenre() {
//        Genre expectedGenre = new Genre(2, "horror");
//        genreDaoJdbc.insertGenre(expectedGenre);
//        Genre actualGenre = genreDaoJdbc.getGenreById(expectedGenre.getId());
//        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
//    }
//
//    @DisplayName("удалять автора по id")
//    @Test
//    void shouldCorrectDeleteGenre() {
//        assertThatCode(() -> genreDaoJdbc.getGenreById(EXISTING_GENRE_ID)).doesNotThrowAnyException();
//        genreDaoJdbc.deleteGenreById(EXISTING_GENRE_ID);
//        assertThatThrownBy(() -> genreDaoJdbc.getGenreById(EXISTING_GENRE_ID)).isInstanceOf(EmptyResultDataAccessException.class);
//    }
//}