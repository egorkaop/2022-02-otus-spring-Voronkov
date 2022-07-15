package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.GenreRepository;
import ru.otus.dto.GenreDto;
import ru.otus.exceptions.GenreNotFoundException;
import ru.otus.service.GenreService;
import ru.otus.utils.GenreDtoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDtoMapper genreDtoMapper;
    private final GenreRepository genreRepository;


    @Override
    @Transactional(readOnly = true)
    @PostAuthorize("hasAnyRole('ADMIN','USER')")
    public List<GenreDto> getAllGenres() {
        return genreDtoMapper.convertListGenresToDto(genreRepository.findAll());
    }


    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteGenreById(long id) {
        try {
            genreRepository.deleteById(id);
        } catch (Exception e) {
            throw new GenreNotFoundException("По заданному id жанр не найден");
        }
    }
}
