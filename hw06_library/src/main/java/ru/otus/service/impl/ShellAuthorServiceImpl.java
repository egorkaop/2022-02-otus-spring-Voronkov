package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.exceptions.AuthorNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.ShellAuthorService;
import ru.otus.utils.BookListMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellAuthorServiceImpl implements ShellAuthorService {
    private final AuthorDao authorDao;
    private final IOService ioService;
    private final BookListMapper bookListMapper;

    @Override
    public void getAuthorCount() {
        ioService.outputText("Количество авторов: " + authorDao.count());
    }

    @Override
    public void getAuthorByID() {
        ioService.outputFormatText("Введите id автора");
        long id = Long.parseLong(ioService.inputText());
        try {
            ioService.outputText(authorDao.getAuthorById(id).toString());
        } catch (Exception e) {
            throw new AuthorNotFoundException("По задданому id автор не найден");
        }
    }

    @Override
    @Transactional
    public void getAllAuthors() {
        ioService.outputText(authorDao.getAllAuthor().toString());
    }

    @Override
    @Transactional
    public void insertAuthor() {
        ioService.outputText("Введите имя автора");
        String name = ioService.inputText();
        ioService.outputText("Введите фамилию автора");
        String surname = ioService.inputText();
        ioService.outputText("Введите через запятую id книг, которые написал автор. Если книги нет в библиотеке, вы можете её добавить (bi)");
        String booksId=ioService.inputText();
        List<Book> bookList = bookListMapper.getBookList(booksId);
        authorDao.insertAuthor(new Author(name,surname,bookList));
    }

    @Override
    public void deleteAuthorById() {
        ioService.outputText("Введите id автора для удаления");
        long id = Long.parseLong(ioService.inputText());
        try {
            authorDao.deleteAuthorById(id);
        } catch (Exception e) {
            throw new AuthorNotFoundException("По задданому id автор не найден");
        }
    }
}
