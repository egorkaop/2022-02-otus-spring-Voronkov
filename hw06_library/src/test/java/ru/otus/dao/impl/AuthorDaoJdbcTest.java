//package ru.otus.dao.impl;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.dao.EmptyResultDataAccessException;
//import ru.otus.domain.Author;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@DisplayName("Dao для работы с авторами должно")
//@JdbcTest
//@Import(AuthorDaoJdbc.class)
//class AuthorDaoJdbcTest {
//    private static final int EXPECTED_AUTHORS_COUNT = 1;
//    private static final int EXISTING_AUTHOR_ID = 1;
//    private static final String EXISTING_AUTHOR_NAME = "Martin";
//    private static final String EXISTING_AUTHOR_SURNAME = "George Raymond Richard";
//    @Autowired
//    private AuthorDaoJdbc authorDaoJdbc;
//
//    @DisplayName("правильно возвращать количество записей")
//    @Test
//    void shouldReturnCorrectCountOfAuthor() {
//        int actualAuthorsCount = authorDaoJdbc.count();
//        assertThat(actualAuthorsCount).isEqualTo(EXPECTED_AUTHORS_COUNT);
//    }
//
//    @DisplayName("правильно возвращать автора по id")
//    @Test
//    void shouldReturnCorrectAuthorById() {
//        Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_SURNAME);
//        Author actualAuthor = authorDaoJdbc.getAuthorByID(EXISTING_AUTHOR_ID);
//        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
//    }
//
//    @DisplayName("правильно возвращать список с авторами")
//    @Test
//    void shouldReturnCorrectAuthorList() {
//        List<Author> expectedAuthorList = List.of(new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME, EXISTING_AUTHOR_SURNAME));
//        List<Author> actualAuthorList = authorDaoJdbc.getAllAuthor();
//        assertThat(actualAuthorList).usingRecursiveComparison().isEqualTo(expectedAuthorList);
//    }
//
//    @DisplayName("добавлять автора")
//    @Test
//    void shouldInsertAuthor() {
//        Author expectedAuthor = new Author(2, "Egor", "Voronkov");
//        authorDaoJdbc.insertAuthor(expectedAuthor);
//        Author actualAuthor = authorDaoJdbc.getAuthorByID(expectedAuthor.getId());
//        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
//    }
//
//    @DisplayName("удалять автора по id")
//    @Test
//    void shouldCorrectDeleteAuthor() {
//        assertThatCode(() -> authorDaoJdbc.getAuthorByID(EXISTING_AUTHOR_ID)).doesNotThrowAnyException();
//        authorDaoJdbc.deleteAuthorById(EXISTING_AUTHOR_ID);
//        assertThatThrownBy(() -> authorDaoJdbc.getAuthorByID(EXISTING_AUTHOR_ID)).isInstanceOf(EmptyResultDataAccessException.class);
//    }
//}