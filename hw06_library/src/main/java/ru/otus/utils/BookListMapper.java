package ru.otus.utils;

import ru.otus.domain.Book;

import java.util.List;

public interface BookListMapper {
    List<Book> getBookList(String stringId);
}
