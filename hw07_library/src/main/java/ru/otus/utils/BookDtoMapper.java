package ru.otus.utils;

import ru.otus.domain.Book;
import ru.otus.dto.BookDto;

import java.util.List;

public interface BookDtoMapper {
    BookDto convertBookToDto(Book book);

    List<BookDto> convertListBooksToDto(List<Book> bookList);

}
