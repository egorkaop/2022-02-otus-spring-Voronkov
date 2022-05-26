package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookRepository;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Genre;
import ru.otus.dto.GenreDto;
import ru.otus.exceptions.GenreNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.ShellGenreService;
import ru.otus.utils.GenreDtoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellGenreServiceImpl implements ShellGenreService {
    private final IOService ioService;
    private final GenreDtoMapper genreDtoMapper;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public void getGenreCount() {
        ioService.outputText("Количество жанров: " + genreRepository.count());
    }

    @Override
    public void getGenreByName() {
        ioService.outputText("Введите название жанра");
        String genreName = ioService.inputText();
        try {
            List<GenreDto> genreDto = genreDtoMapper.convertListGenresToDto(genreRepository.findByName(genreName));
            ioService.outputText(genreDto.toString());
        } catch (Exception e) {
            throw new GenreNotFoundException("По заданному id жанр не найден");
        }
    }

    @Override
    public void getAllGenres() {
        List<GenreDto> genreDtoList = genreDtoMapper.convertListGenresToDto(genreRepository.findAll());
        ioService.outputText(genreDtoList.toString());
    }

    @Override
    public void insertGenre() {
        ioService.outputText("Введите название жанра");
        String name = ioService.inputText();
        genreRepository.save(new Genre(name));
    }

    @Override
    public void deleteGenreByName() {
        ioService.outputText("Введите название жанра для удаления");
        String genreName = ioService.inputText();
        try {
            genreRepository.deleteByName(genreName);
        } catch (Exception e) {
            throw new GenreNotFoundException("По заданному названию жанр не найден");
        }
    }

    @Override
    public void updateGenreNameByName() {
        ioService.outputText("Введите название жанра для замены");
        String oldGenreName = ioService.inputText();
        ioService.outputText("Введите новое название жанра");
        String newGenreName = ioService.inputText();
        genreRepository.findByName(oldGenreName).stream()
                .peek(x -> x.setName(newGenreName))
                .forEach(genreRepository::save);
    }

}
