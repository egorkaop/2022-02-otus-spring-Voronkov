package ru.otus.service;

import ru.otus.domain.Book;
import ru.otus.dto.BookDto;

import java.util.List;

public interface BookService {
    long getBookCount();

    BookDto getBookByID(long id);

    List<BookDto> getAllBooks();

    void insertBook();

    void deleteBookById(long id);

    void updateBookTitleById(long id,String title);
}
