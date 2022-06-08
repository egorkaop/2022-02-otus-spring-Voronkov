package ru.otus.service;

import ru.otus.dto.BookDto;

import java.util.List;

public interface BookService {
    long getBookCount();

    BookDto getBookByID(long id);

    List<BookDto> getAllBooks();

    void insertBook(String title, long[] authorsId,long[] genresId);

    void deleteBookById(long id);

    void updateBookTitleById(long id,String title);

    List<BookDto> getBooksByAuthorId(long id);

    List<BookDto> getBooksByGenreId(long id);
}
