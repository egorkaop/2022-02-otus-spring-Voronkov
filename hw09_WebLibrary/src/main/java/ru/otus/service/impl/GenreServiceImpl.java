package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookRepository;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Genre;
import ru.otus.dto.GenreDto;
import ru.otus.exceptions.GenreNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.GenreService;
import ru.otus.utils.GenreDtoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final IOService ioService;
    private final GenreDtoMapper genreDtoMapper;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public long getGenreCount() {
        return genreRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDto getGenreByID(long id) {
        try {
            GenreDto genreDto = genreDtoMapper.convertGenreToDto(genreRepository.getById(id));
            ioService.outputText(genreDto.toString());
            return genreDto;
        } catch (Exception e) {
            throw new GenreNotFoundException("По заданному id жанр не найден");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreDto> getAllGenres() {
        List<GenreDto> genreDtoList = genreDtoMapper.convertListGenresToDto(genreRepository.findAll());
        ioService.outputText(genreDtoList.toString());
        return genreDtoList;
    }

    @Override
    @Transactional
    public void insertGenre() {
        ioService.outputText("Введите название жанра");
        String name = ioService.inputText();
        ioService.outputText("Введите id книги");
        genreRepository.save(new Genre(name));
    }

    @Override
    @Transactional
    public void deleteGenreById() {
        ioService.outputText("Введите id жанра для удаления");
        long id = Long.parseLong(ioService.inputText());
        try {
            genreRepository.deleteById(id);
        } catch (Exception e) {
            throw new GenreNotFoundException("По заданному id жанр не найден");
        }
    }

    @Override
    @Transactional
    public void updateGenreNameById() {
        ioService.outputText("Введите id жанра для замены");
        long id = Long.parseLong(ioService.inputText());
        ioService.outputText("Введите новое название жанра");
        String name = ioService.inputText();
        genreRepository.updateGenreNameById(id, name);
    }

    @Override
    public List<GenreDto> getAllGenresByBookId() {
        ioService.outputText("Введите id книги");
        long id = Long.parseLong(ioService.inputText());
        List<GenreDto> genreDtoList = genreDtoMapper.convertListGenresToDto(genreRepository.findGenreByBookListContains(bookRepository.getById(id)));
        ioService.outputText(genreDtoList.toString());
        return genreDtoList;
    }
}
