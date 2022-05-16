package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
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
    private final GenreDao genreDao;
    private final IOService ioService;
    private final GenreDtoMapper genreDtoMapper;
    private final BookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public void getGenreCount() {
        ioService.outputText("Количество жанров: " + genreDao.count());
    }

    @Override
    @Transactional(readOnly = true)
    public void getGenreByID() {
        ioService.outputText("Введите id жанра");
        long id = Long.parseLong(ioService.inputText());
        try {
            GenreDto genreDto = genreDtoMapper.convertGenreToDto(genreDao.getGenreById(id));
            ioService.outputText(genreDto.toString());
        } catch (Exception e) {
            throw new GenreNotFoundException("По заданному id жанр не найден");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void getAllGenres() {
        List<GenreDto> genreDtoList = genreDtoMapper.convertListGenresToDto(genreDao.getAllGenre());
        ioService.outputText(genreDtoList.toString());
    }

    @Override
    @Transactional
    public void insertGenre() {
        ioService.outputText("Введите название жанра");
        String name = ioService.inputText();
        ioService.outputText("Введите id книги");
        long bookId = Long.parseLong(ioService.inputText());
        genreDao.insertGenre(new Genre(name));
    }

    @Override
    @Transactional
    public void deleteGenreById() {
        ioService.outputText("Введите id жанра для удаления");
        long id = Long.parseLong(ioService.inputText());
        try {
            genreDao.deleteGenreById(id);
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
        genreDao.updateGenreNameById(id, name);
    }
}
