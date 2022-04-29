package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exceptions.GenreNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.ShellGenreService;
import ru.otus.utils.BookListMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellGenreServiceImpl implements ShellGenreService {
    private final GenreDao genreDao;
    private final IOService ioService;
    private final BookListMapper bookListMapper;

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
        ioService.outputText("Введите через запятую id книг, которые соответствуют жанру. Если книги нет в библиотеке, вы можете её добавить (bi)");
        String booksId=ioService.inputText();
        List<Book> bookList = bookListMapper.getBookList(booksId);
        genreDao.insertGenre(new Genre(name,bookList));
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

    @Override
    public void updateGenreNameById() {
        ioService.outputText("Введите id жанра для замены");
        long id = Long.parseLong(ioService.inputText());
        ioService.outputText("Введите новое название жанра");
        String name = ioService.inputText();
        genreDao.updateGenreNameById(id,name);
    }
}
