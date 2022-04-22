package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Genre;
import ru.otus.exceptions.GenreNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.ShellGenreService;

@Service
@RequiredArgsConstructor
public class ShellGenreServiceImpl implements ShellGenreService {
    private final GenreDao genreDao;
    private final IOService ioService;

    @Override
    public void getGenreCount() {
        ioService.outputText("Количество жанров: " + genreDao.count());
    }

    @Override
    public void getGenreByID() {
        ioService.outputFormatText("Введите id жанра");
        long id = Long.parseLong(ioService.inputText());
        try {
            ioService.outputText(genreDao.getGenreById(id).toString());
        } catch (Exception e) {
            throw new GenreNotFoundException("По заданному id жанр не найден");
        }
    }

    @Override
    public void getAllGenres() {
        ioService.outputText(genreDao.getAllGenre().toString());
    }

    @Override
    public void insertGenre() {
        ioService.outputText("Введите название жанра");
        String name = ioService.inputText();
        genreDao.insertGenre(new Genre(name));
    }

    @Override
    public void deleteGenreById() {
        ioService.outputText("Введите id жанра для удаления");
        long id = Long.parseLong(ioService.inputText());
        try {
            genreDao.deleteGenreById(id);
        } catch (Exception e) {
            throw new GenreNotFoundException("По заданному id жанр не найден");
        }
    }
}
