package ru.otus.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.domain.Author;
import ru.otus.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import({BookDaoJdbc.class,AuthorDaoJdbc.class,GenreDaoJdbc.class})
class BookDaoJdbcTest {
    private static final int EXPECTED_BOOKS_COUNT = 1;
    private static final int EXISTING_BOOK_ID = 1;
    private static final long EXISTING_AUTHOR_ID = 1;
    private static final long EXISTING_GENRE_ID = 1;
    private static final String EXISTING_BOOK_NAME = "A song of ice and fire";
    @Autowired
    private BookDaoJdbc bookDaoJdbc;
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @DisplayName("правильно возвращать количество записей")
    @Test
    void shouldReturnCorrectCountOfBook() {
        int actualBooksCount = bookDaoJdbc.count();
        assertThat(actualBooksCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("правильно возвращать книгу по id")
    @Test
    void shouldReturnCorrectBookById() {
        Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME
                , authorDaoJdbc.getAuthorByID(1),genreDaoJdbc.getGenreById(1));
        System.out.println(expectedBook);
        Book actualBook = bookDaoJdbc.getBookById(EXISTING_BOOK_ID);
        System.out.println(actualBook);
        System.out.println(authorDaoJdbc.getAuthorByID(EXISTING_AUTHOR_ID));
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @DisplayName("правильно возвращать список с книгами")
    @Test
    void shouldReturnCorrectBookList() {
        List<Book> expectedBookList = List.of(new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME
                , authorDaoJdbc.getAuthorByID(EXISTING_AUTHOR_ID),genreDaoJdbc.getGenreById(EXISTING_GENRE_ID)));
        List<Book> actualBookList = bookDaoJdbc.getAllBook();
        assertThat(actualBookList).usingRecursiveComparison().isEqualTo(expectedBookList);
    }

    @DisplayName("добавлять книгу")
    @Test
    void shouldInsertBook() {
        Book expectedBook = new Book(2,"second", authorDaoJdbc.getAuthorByID(EXISTING_AUTHOR_ID),genreDaoJdbc.getGenreById(EXISTING_GENRE_ID));
        bookDaoJdbc.insertBook(expectedBook);
        Book actualBook = bookDaoJdbc.getBookById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldCorrectDeleteBook() {
        assertThatCode(() -> bookDaoJdbc.getBookById(EXISTING_BOOK_ID)).doesNotThrowAnyException();
        bookDaoJdbc.deleteBookById(EXISTING_BOOK_ID);
        assertThatThrownBy(() -> bookDaoJdbc.getBookById(EXISTING_BOOK_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}