package ru.otus.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorRepository;
import ru.otus.dao.BookRepository;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.dto.BookDto;
import ru.otus.exceptions.AuthorNotFoundException;
import ru.otus.exceptions.BookNotFoundException;
import ru.otus.service.BookService;
import ru.otus.service.IOService;
import ru.otus.utils.BookDtoMapper;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final IOService ioService;
    private final BookDtoMapper bookDtoMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public long getBookCount() {
        return bookRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getBookByID(long id) {
        try {
            Book book = bookRepository.getById(id);
            BookDto bookDto = bookDtoMapper.convertBookToDto(book);
            return bookDto;
        } catch (Exception e) {
            throw new BookNotFoundException("По заданному id книги не найдено");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = bookDtoMapper.convertListBooksToDto(bookList);
        return bookDtoList;
    }

    @Override
    @Transactional
    public void insertBook(String title, long[] authorsId,long[] genresId) {
        List<Author> authors = getListOfAuthors(authorsId);
        List<Genre> genres = getListOfGenres(genresId);
        bookRepository.save(new Book(title, authors, genres));
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new BookNotFoundException("По заданному id книги не найдено");
        }
    }

    @Override
    @Transactional
    public void updateBookTitleById(long id, String title) {
        bookRepository.updateBookTitleById(id, title);
    }

    @Override
    public List<BookDto> getBooksByAuthorId(long id) {
        Author author = authorRepository.getById(id);
        List<Book> bookList = bookRepository.findAllByAuthorsContains(author);
        return bookDtoMapper.convertListBooksToDto(bookList);
    }

    @Override
    public List<BookDto> getBooksByGenreId(long id) {
        Genre genre = genreRepository.getById(id);
        List<Book> bookList = bookRepository.findAllByGenresContains(genre);
        return bookDtoMapper.convertListBooksToDto(bookList);
    }


    private List<Author> getListOfAuthors(long[] authorsId) {
        List<Author> authorList = Arrays.stream(authorsId)
                .mapToObj(authorRepository::getById)
                .collect(Collectors.toList());
        return authorList;
    }

    private List<Genre> getListOfGenres(long[] genresId) {
        List<Genre> genreList = Arrays.stream(genresId)
                .mapToObj(genreRepository::getById)
                .collect(Collectors.toList());
        return genreList;
    }
}
