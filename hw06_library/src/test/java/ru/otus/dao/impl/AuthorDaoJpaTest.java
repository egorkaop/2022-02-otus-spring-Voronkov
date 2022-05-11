package ru.otus.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;
import ru.otus.domain.Book;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("dao для работы с авторами должно ")
@DataJpaTest
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {
    private static final long EXPECTED_COUNT_OF_AUTHORS = 1;
    private static final long EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Martin";
    @Autowired
    private AuthorDaoJpa authorDaoJpa;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("правильно возвращать количество авторов")
    void shouldReturnCorrectCountOfAuthors() {
        long actualCount = authorDaoJpa.count();
        assertThat(actualCount).isEqualTo(EXPECTED_COUNT_OF_AUTHORS);
    }

    @Test
    @DisplayName("правильно возвращать автора по id")
    void shouldReturnCorrectAuthorById() {
        Author expectedAuthor = testEntityManager.find(Author.class, EXISTING_AUTHOR_ID);
        Author actualAuthor = authorDaoJpa.getAuthorById(EXISTING_AUTHOR_ID);
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("правильно возвращать список авторов")
    void shouldReturnCorrectListOfAuthor() {
        List<Author> expectedAuthorList = List.of(testEntityManager.find(Author.class, EXISTING_AUTHOR_ID));
        List<Author> actualAuthorList = authorDaoJpa.getAllAuthor();
        assertThat(actualAuthorList).isEqualTo(expectedAuthorList);
    }

    @Test
    @DisplayName("добавлять автора")
    void shouldInsertAuthor() {
        Author expectedAuthor = new Author("Egor", "Voronkov");
        authorDaoJpa.insertAuthor(expectedAuthor);
        Author actualAuthor = authorDaoJpa.getAuthorById(2);
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("удалять автора")
    void shouldDeleteAuthor() {
        Author author = testEntityManager.find(Author.class, EXISTING_AUTHOR_ID);
        assertThat(author).isNotNull();
        testEntityManager.detach(author);
        authorDaoJpa.deleteAuthorById(EXISTING_AUTHOR_ID);
        Author deletedAuthor = testEntityManager.find(Author.class, EXISTING_AUTHOR_ID);
        assertThat(deletedAuthor).isNull();
    }

    @Test
    @DisplayName("обновлять имя автора")
    void shouldCorrectUpdateAuthorNameByID() {
        Author oldAuthor = testEntityManager.find(Author.class, EXISTING_AUTHOR_ID);
        String oldName = oldAuthor.getName();
        assertThat(oldName).isEqualTo(EXISTING_AUTHOR_NAME);
        authorDaoJpa.updateAuthorNameById(EXISTING_AUTHOR_ID, "egor");
        testEntityManager.detach(oldAuthor);
        String newName = testEntityManager.find(Author.class, EXISTING_AUTHOR_ID).getName();
        assertThat(newName).isEqualTo("egor");
    }
}