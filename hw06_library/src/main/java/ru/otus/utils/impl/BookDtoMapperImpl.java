package ru.otus.utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.dto.BookDto;
import ru.otus.utils.BookDtoMapper;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookDtoMapperImpl implements BookDtoMapper {
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public BookDto convertBookToDto(Book book) {
        String title = book.getTitle();
        Author author = authorDao.getAuthorById(book.getAuthor_id());
        Genre genre = genreDao.getGenreById(book.getGenre_id());
        return new BookDto(title, author, genre);
    }

    @Override
    public List<BookDto> convertListBooksToDto(List<Book> bookList) {
        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book : bookList) {
            bookDtoList.add(convertBookToDto(book));
        }
        return bookDtoList;
    }
}
