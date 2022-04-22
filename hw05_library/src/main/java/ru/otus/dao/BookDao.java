package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;

public interface BookDao {
    int count();

    Book getBookById(long id);

    List<Book> getAllBook();

    void insertBook(Book book);

    void deleteBookById(long id);
}
