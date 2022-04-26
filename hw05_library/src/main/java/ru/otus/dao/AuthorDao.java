package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();

    Author getAuthorByID(long id);

    List<Author> getAllAuthor();

    void insertAuthor(Author author);

    void deleteAuthorById(long id);
}
