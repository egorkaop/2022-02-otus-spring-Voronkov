package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exceptions.AuthorNotFoundException;
import ru.otus.exceptions.BookNotFoundException;
import ru.otus.exceptions.GenreNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.ShellBookService;

@Service
@RequiredArgsConstructor
public class ShellBookServiceImpl implements ShellBookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final IOService ioService;

    @Override
    public void getBookCount() {
        ioService.outputText("Количество книг: " + bookDao.count());
    }

    @Override
    public void getBookByID() {
        ioService.outputText("Введите id книги");
        long id = Long.parseLong(ioService.inputText());
        try {
            ioService.outputText(bookDao.getBookById(id).toString());
        } catch (Exception e) {
            throw new BookNotFoundException("По заданному id книги не найдено");
        }
    }

    @Override
    public void getAllBooks() {
        ioService.outputText(bookDao.getAllBook().toString());
    }

    @Override
    public void insertBook() {
        ioService.outputText("Введите название книги");
        String title = ioService.inputText();
        ioService.outputText("Введите id автора");
        Author author = getAuthor(Long.parseLong(ioService.inputText()));
        ioService.outputText("Введите id жанра");
        Genre genre = getGenre(Long.parseLong(ioService.inputText()));
        bookDao.insertBook(new Book(title, author, genre));
    }

    @Override
    public void deleteBookById() {
        ioService.outputText("Введите id ниги для удаления");
        long id = Long.parseLong(ioService.inputText());
        try {
            bookDao.deleteBookById(id);
        } catch (Exception e) {
            throw new BookNotFoundException("По заданному id книги не найдено");
        }

    }

    private Author getAuthor(long id) {
        try {
            return authorDao.getAuthorByID(id);
        }
        catch (Exception e){
            throw new AuthorNotFoundException("По задданому id автор не найден");
        }
    }

    private Genre getGenre(long id) {
        try {
            return genreDao.getGenreById(id);
        }
        catch (Exception e){
            throw new GenreNotFoundException("По заданному id жанр не найден");
        }
    }
}
