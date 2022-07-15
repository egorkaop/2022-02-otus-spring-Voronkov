package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorRepository;
import ru.otus.dao.BookRepository;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.dto.BookDto;
import ru.otus.dto.BookInsertDto;
import ru.otus.dto.BookUpdateDto;
import ru.otus.exceptions.BookNotFoundException;
import ru.otus.service.BookService;
import ru.otus.utils.BookDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDtoMapper bookDtoMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;


    @Override
    @Transactional(readOnly = true)
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
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
    @PostAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public List<BookDto> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = bookDtoMapper.convertListBooksToDto(bookList);
        return bookDtoList;
    }

    @Override
    @Transactional
    public void insertBook(BookInsertDto bookInsertDto) {
        List<Author> authors = getListOfAuthors(bookInsertDto.getAuthors());
        List<Genre> genres = getListOfGenres(bookInsertDto.getGenres());
        bookRepository.save(new Book(bookInsertDto.getTitle(), authors, genres));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteBookById(long id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new BookNotFoundException("По заданному id книги не найдено");
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public void updateBookTitleById(long id, BookUpdateDto bookUpdateDto) {
        bookRepository.updateBookTitleById(id, bookUpdateDto.getTitle());
    }


    private List<Author> getListOfAuthors(List<Long> authorsId) {
        return authorsId.stream()
                .map(authorRepository::getById)
                .collect(Collectors.toList());
    }

    private List<Genre> getListOfGenres(List<Long> genresId) {
        return genresId.stream()
                .map(genreRepository::getById)
                .collect(Collectors.toList());
    }
}
