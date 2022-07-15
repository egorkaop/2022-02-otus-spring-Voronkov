package ru.otus.utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Genre;
import ru.otus.dto.GenreDto;
import ru.otus.utils.GenreDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GenreDtoMapperImpl implements GenreDtoMapper {

    @Override
    public GenreDto convertGenreToDto(Genre genre) {
        long id = genre.getId();
        String name = genre.getName();
        return new GenreDto(id, name);
    }

    @Override
    public List<GenreDto> convertListGenresToDto(List<Genre> genreList) {
        return genreList.stream()
                .map(this::convertGenreToDto)
                .collect(Collectors.toList());
    }
}
