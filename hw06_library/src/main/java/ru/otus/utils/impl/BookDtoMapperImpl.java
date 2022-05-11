package ru.otus.utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Book;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.dto.GenreDto;
import ru.otus.utils.AuthorDtoMapper;
import ru.otus.utils.BookDtoMapper;
import ru.otus.utils.GenreDtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookDtoMapperImpl implements BookDtoMapper {
    private final AuthorDtoMapper authorDtoMapper;
    private final GenreDtoMapper genreDtoMapper;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public BookDto convertBookToDto(Book book) {
        String title = book.getTitle();
        List<AuthorDto> authorDtoList = authorDtoMapper.convertListAuthorsToDto(book.getAuthors());
        List<GenreDto> genreDtoList = genreDtoMapper.convertListGenresToDto(book.getGenres());
        return new BookDto(title, authorDtoList, genreDtoList);
    }

    @Override
    public List<BookDto> convertListBooksToDto(List<Book> bookList) {
        return bookList.stream()
                .map(this::convertBookToDto)
                .collect(Collectors.toList());
    }
}
