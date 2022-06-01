package ru.otus.service.impl;

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
import ru.otus.service.IOService;
import ru.otus.service.BookService;
import ru.otus.utils.BookDtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void insertBook() {
        ioService.outputText("Введите название книги");
        String title = ioService.inputText();
        List<Author> authors = getListOfAuthors();
        List<Genre> genres = getListOfGenres();
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
    public void updateBookTitleById(long id,String title) {
        bookRepository.updateBookTitleById(id,title);
        System.out.println(id + title);
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
}
