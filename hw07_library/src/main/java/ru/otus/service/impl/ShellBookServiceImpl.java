package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.*;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.dto.BookDto;
import ru.otus.exceptions.AuthorNotFoundException;
import ru.otus.exceptions.BookNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.ShellBookService;
import ru.otus.utils.BookDtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShellBookServiceImpl implements ShellBookService {
    private final IOService ioService;
    private final BookDtoMapper bookDtoMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public void getBookCount() {
        ioService.outputText("Количество книг: " + bookRepository.count());
    }

    @Override
    @Transactional(readOnly = true)
    public void getBookByID() {
        ioService.outputText("Введите id книги");
        long id = Long.parseLong(ioService.inputText());
        try {
            Book book = bookRepository.getById(id);
            BookDto bookDto = bookDtoMapper.convertBookToDto(addAuthorsAndGenresToBook(book));
            ioService.outputText(bookDto.toString());
        } catch (Exception e) {
            throw new BookNotFoundException("По заданному id книги не найдено");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void getAllBooks() {
        List<Book> bookList = bookRepository.findAll().stream()
                .map(this::addAuthorsAndGenresToBook)
                .collect(Collectors.toList());
        List<BookDto> bookDtoList = bookDtoMapper.convertListBooksToDto(bookList);
        ioService.outputText(bookDtoList.toString());
    }

    @Override
    @Transactional
    public void insertBook() {
        ioService.outputText("Введите название книги");
        String title = ioService.inputText();
        List<Author> authors = getListOfAuthors();
        List<Genre> genres = getListOfGenres();
        bookRepository.save(new Book(title, authors, genres));
    }

    @Override
    @Transactional
    public void deleteBookById() {
        ioService.outputText("Введите id ниги для удаления");
        long id = Long.parseLong(ioService.inputText());
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new BookNotFoundException("По заданному id книги не найдено");
        }
    }

    @Override
    @Transactional
    public void updateBookTitleById() {
        ioService.outputText("Введите id книги для замены");
        long id = Long.parseLong(ioService.inputText());
        ioService.outputText("Введите новое название книги");
        String title = ioService.inputText();
        bookRepository.updateBookTitleById(id,title);
    }

    private List<Author> getListOfAuthors(){
        List<Author> authorList = new ArrayList<>();
        while (true){
            ioService.outputText("Введите id автора");
            long authorId=Long.parseLong(ioService.inputText());
            Optional.ofNullable(authorRepository.getById(authorId))
                    .map(authorList::add)
                    .orElseThrow(() -> new AuthorNotFoundException("Вы ввели несуществующего автора"));
            ioService.outputText("Есть ли ещё автор? yes/no");
            if(!ioService.inputText().equals("yes")){
                break;
            }
        }
        return authorList;
    }

    private List<Genre> getListOfGenres(){
        List<Genre> genreList = new ArrayList<>();
        while (true){
            ioService.outputText("Введите id жанра");
            long genreId=Long.parseLong(ioService.inputText());
            Optional.ofNullable(genreRepository.getById(genreId))
                    .map(genreList::add)
                    .orElseThrow(() -> new AuthorNotFoundException("Вы ввели несуществующий жанр"));
            ioService.outputText("Есть ли ещё жанр? yes/no");
            if(!ioService.inputText().equals("yes")){
                break;
            }
        }
        return genreList;
    }

    private Book addAuthorsAndGenresToBook(Book book){
        book.setAuthors(authorRepository.findAuthorByBook_Id(book.getId()));
        book.setGenres(genreRepository.findByBook_Id(book.getId()));
        return book;
    }
}
