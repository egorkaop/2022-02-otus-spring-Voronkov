package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
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
    private final BookDao bookDao;
    private final IOService ioService;
    private final BookDtoMapper bookDtoMapper;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    @Transactional(readOnly = true)
    public void getBookCount() {
        ioService.outputText("Количество книг: " + bookDao.count());
    }

    @Override
    @Transactional(readOnly = true)
    public void getBookByID() {
        ioService.outputText("Введите id книги");
        long id = Long.parseLong(ioService.inputText());
        try {
            Book book = bookDao.getBookById(id);
            BookDto bookDto = bookDtoMapper.convertBookToDto(book);
            ioService.outputText(bookDto.toString());
        } catch (Exception e) {
            throw new BookNotFoundException("По заданному id книги не найдено");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void getAllBooks() {
        List<Book> bookList = bookDao.getAllBook();
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
        bookDao.insertBook(new Book(title, authors, genres));
    }

    @Override
    @Transactional
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
    @Transactional
    public void updateBookTitleById() {
        ioService.outputText("Введите id книги для замены");
        long id = Long.parseLong(ioService.inputText());
        ioService.outputText("Введите новое название книги");
        String title = ioService.inputText();
        bookDao.updateBookTitleById(id, title);
    }

    private List<Author> getListOfAuthors(){
        List<Author> authorList = new ArrayList<>();
        while (true){
            ioService.outputText("Введите id автора");
            long authorId=Long.parseLong(ioService.inputText());
            Optional.ofNullable(authorDao.getAuthorById(authorId))
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
            Optional.ofNullable(genreDao.getGenreById(genreId))
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
