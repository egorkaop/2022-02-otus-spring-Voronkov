package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;

public interface BookDao {
    long count();

    Book getBookById(long id);

    List<Book> getAllBook();

    void insertBook(Book book);

    void deleteBookById(long id);

    void updateBookTitleById(long id,String title);
}
