package ru.otus.utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Genre;
import ru.otus.dto.BookDto;
import ru.otus.dto.GenreDto;
import ru.otus.utils.BookDtoMapper;
import ru.otus.utils.GenreDtoMapper;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreDtoMapperImpl implements GenreDtoMapper {
    private final BookDtoMapper bookDtoMapper;

    @Override
    public GenreDto convertGenreToDto(Genre genre) {
        String name = genre.getName();
        List<BookDto> bookDtoList = bookDtoMapper.convertListBooksToDto(genre.getBooks());
        return new GenreDto(name, bookDtoList);
    }

    @Override
    public List<GenreDto> convertListGenresToDto(List<Genre> genreList) {
        List<GenreDto> genreDtoList = new ArrayList<>();
        for (Genre genre : genreList) {
            genreDtoList.add(convertGenreToDto(genre));
        }
        return genreDtoList;
    }
}
