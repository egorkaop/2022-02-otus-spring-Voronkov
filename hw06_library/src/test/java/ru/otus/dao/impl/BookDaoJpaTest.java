package ru.otus.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Book;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("dao для работы с книгами должно ")
@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTest {
    private static final long EXPECTED_COUNT_OF_BOOKS = 1;
    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_AUTHOR_TITLE = "A song of ice and fire";
    @Autowired
    private BookDaoJpa bookDaoJpa;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("правильно возвращать количество книг")
    void shouldReturnCorrectCountOfBooks() {
        long actualCount = bookDaoJpa.count();
        assertThat(actualCount).isEqualTo(EXPECTED_COUNT_OF_BOOKS);
    }

    @Test
    @DisplayName("правильно возвращать книгу по id")
    void shouldReturnCorrectBookById() {
        Book expectedBook = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        Book actualBook = bookDaoJpa.getBookById(EXISTING_BOOK_ID);
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("правильно возвращать список книг")
    void shouldReturnCorrectListOfBook() {
        List<Book> expectedBookList = List.of(testEntityManager.find(Book.class, EXISTING_BOOK_ID));
        List<Book> actualBookList = bookDaoJpa.getAllBook();
        assertThat(actualBookList).isEqualTo(expectedBookList);
    }

    @Test
    @DisplayName("добавлять книгу")
    void shouldInsertBook() {
        Book expectedBook = new Book("it", 1, 1);
        bookDaoJpa.insertBook(expectedBook);
        Book actualBook = bookDaoJpa.getBookById(2);
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("удалять книгу")
    void shouldDeleteBook() {
        Book book = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        assertThat(book).isNotNull();
        testEntityManager.detach(book);
        bookDaoJpa.deleteBookById(EXISTING_BOOK_ID);
        Book deletedBook = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        assertThat(deletedBook).isNull();
    }

    @Test
    @DisplayName("обновлять название книги")
    void shouldCorrectUpdateBookTitleByID() {
        Book oldBook = testEntityManager.find(Book.class, EXISTING_BOOK_ID);
        String oldTitle = oldBook.getTitle();
        assertThat(oldTitle).isEqualTo(EXISTING_AUTHOR_TITLE);
        bookDaoJpa.updateBookTitleById(EXISTING_BOOK_ID, "it");
        testEntityManager.detach(oldBook);
        String newTitle = testEntityManager.find(Book.class, EXISTING_BOOK_ID).getTitle();
        assertThat(newTitle).isEqualTo("it");
    }
}