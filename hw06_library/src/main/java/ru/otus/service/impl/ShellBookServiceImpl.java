package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookDao;
import ru.otus.domain.Book;
import ru.otus.dto.BookDto;
import ru.otus.exceptions.BookNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.ShellBookService;
import ru.otus.utils.BookDtoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellBookServiceImpl implements ShellBookService {
    private final BookDao bookDao;
    private final IOService ioService;
    private final BookDtoMapper bookDtoMapper;

    @Override
    public void getBookCount() {
        ioService.outputText("Количество книг: " + bookDao.count());
    }

    @Override
    public void getBookByID() {
        ioService.outputText("Введите id книги");
        long id = Long.parseLong(ioService.inputText());
        try {
            BookDto bookDto = bookDtoMapper.convertBookToDto(bookDao.getBookById(id));
            ioService.outputText(bookDto.toString());
        } catch (Exception e) {
            throw new BookNotFoundException("По заданному id книги не найдено");
        }
    }

    @Override
    public void getAllBooks() {
        List<BookDto> bookDtoList = bookDtoMapper.convertListBooksToDto(bookDao.getAllBook());
        ioService.outputText(bookDtoList.toString());
    }

    @Override
    public void insertBook() {
        ioService.outputText("Введите название книги");
        String title = ioService.inputText();
        ioService.outputText("Введите id автора");
        long authorId = Long.parseLong(ioService.inputText());
        ioService.outputText("Введите id жанра");
        long genreId = Long.parseLong(ioService.inputText());
        bookDao.insertBook(new Book(title, authorId, genreId));
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

    @Override
    public void updateBookTitleById() {
        ioService.outputText("Введите id книги для замены");
        long id = Long.parseLong(ioService.inputText());
        ioService.outputText("Введите новое название книги");
        String title = ioService.inputText();
        bookDao.updateBookTitleById(id, title);
    }
}
