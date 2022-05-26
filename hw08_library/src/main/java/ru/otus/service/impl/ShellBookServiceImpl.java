package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorRepository;
import ru.otus.dao.BookRepository;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.dto.BookDto;
import ru.otus.exceptions.BookNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.ShellBookService;
import ru.otus.utils.BookDtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShellBookServiceImpl implements ShellBookService {
    private final IOService ioService;
    private final BookDtoMapper bookDtoMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public void getBookCount() {
        ioService.outputText("Количество книг: " + bookRepository.count());
    }

    @Override
    public void getBookByTitle() {
        ioService.outputText("Введите название книги");
        String bookTitle = ioService.inputText();
        try {
            BookDto bookDto = bookDtoMapper.convertBookToDto(bookRepository.findByTitle(bookTitle));
            ioService.outputText(bookDto.toString());
        } catch (Exception e) {
            throw new BookNotFoundException("По заданному названию книги не найдено");
        }
    }

    @Override
    public void getAllBooks() {
        List<BookDto> bookDtoList = bookDtoMapper.convertListBooksToDto(bookRepository.findAll());
        ioService.outputText(bookDtoList.toString());
    }

    @Override
    public void insertBook() {
        ioService.outputText("Введите название книги");
        String bookTitle = ioService.inputText();
        List<Author> authors = getListOfAuthors();
        List<Genre> genres = getListOfGenres();
        bookRepository.save(new Book(bookTitle, authors, genres));
    }

    @Override
    public void deleteBookByTitle() {
        ioService.outputText("Введите название книги для удаления");
        String bookTitle = ioService.inputText();
        try {
            bookRepository.deleteByTitle(bookTitle);
        } catch (Exception e) {
            throw new BookNotFoundException("По заданному названию книги не найдено");
        }
    }

    @Override
    public void updateBookTitleByTitle() {
        ioService.outputText("Введите название книги для замены");
        String oldTitle = ioService.inputText();
        ioService.outputText("Введите новое название книги");
        String newTitle = ioService.inputText();
        bookRepository.findByTitle(oldTitle).setTitle(newTitle);
        Optional.ofNullable(bookRepository.findByTitle(oldTitle)).stream()
                .peek(x -> x.setTitle(newTitle))
                .forEach(bookRepository::save);
    }

    private List<Author> getListOfAuthors() {
        List<Author> authorList = new ArrayList<>();
        while (true) {
            ioService.outputText("Введите имя автора");
            String authorName = ioService.inputText();
            authorList.addAll(authorRepository.findByName(authorName));
            ioService.outputText("Есть ли ещё автор? yes/no");
            if (!ioService.inputText().equals("yes")) {
                break;
            }
        }
        return authorList;
    }

    private List<Genre> getListOfGenres() {
        List<Genre> genreList = new ArrayList<>();
        while (true) {
            ioService.outputText("Введите название жанра");
            String genreName = ioService.inputText();
            genreList.addAll(genreRepository.findByName(genreName));
            ioService.outputText("Есть ли ещё жанр? yes/no");
            if (!ioService.inputText().equals("yes")) {
                break;
            }
        }
        return genreList;
    }
}
