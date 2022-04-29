package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorDao {
    long count();

    Author getAuthorById(long id);

    List<Author> getAllAuthor();

    void insertAuthor(Author author);

    void deleteAuthorById(long id);

    void updateAuthorNameById(long id,String name);
}
