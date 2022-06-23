package ru.otus.service;

import ru.otus.dto.BookDto;

import java.util.List;
import ru.otus.dto.BookInsertDto;
import ru.otus.dto.BookUpdateDto;

public interface BookService {
    long getBookCount();

    BookDto getBookByID(long id);

    List<BookDto> getAllBooks();

    void insertBook(BookInsertDto bookInsertDto);

    void deleteBookById(long id);

    void updateBookTitleById(long id, BookUpdateDto bookUpdateDto);

    List<BookDto> getBooksByAuthorId(long id);

    List<BookDto> getBooksByGenreId(long id);
}
