package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorRepository;
import ru.otus.dao.BookRepository;
import ru.otus.domain.Author;
import ru.otus.dto.AuthorDto;
import ru.otus.exceptions.AuthorNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.AuthorService;
import ru.otus.utils.AuthorDtoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final IOService ioService;
    private final AuthorDtoMapper authorDtoMapper;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    @Override
    @Transactional(readOnly = true)
    public long getAuthorCount() {
        return authorRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDto getAuthorByID(long id) {
        try {
            return authorDtoMapper.convertAuthorToDto(authorRepository.getById(id));
        } catch (Exception e) {
            throw new AuthorNotFoundException("По задданому id автор не найден");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        return authorDtoMapper.convertListAuthorsToDto(authorRepository.findAll());
    }

    @Override
    @Transactional
    public void insertAuthor() {
        ioService.outputText("Введите имя автора");
        String name = ioService.inputText();
        ioService.outputText("Введите фамилию автора");
        String surname = ioService.inputText();
        authorRepository.save(new Author(name, surname));
    }

    @Override
    @Transactional
    public void deleteAuthorById(long id) {
        try {
            authorRepository.deleteById(id);
        } catch (Exception e) {
            throw new AuthorNotFoundException("По задданому id автор не найден");
        }
    }

    @Override
    @Transactional
    public void updateAuthorNameById() {
        ioService.outputText("Введите id автора для замены");
        long id = Long.parseLong(ioService.inputText());
        ioService.outputText("Введите новое имя автора");
        String name = ioService.inputText();
        authorRepository.updateAuthorNameById(id,name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthorsByBookId() {
        ioService.outputText("Введите id книги");
        long id = Long.parseLong(ioService.inputText());
        List<AuthorDto> authorDtoList = authorDtoMapper.convertListAuthorsToDto(authorRepository.findAuthorByBookListContains(bookRepository.getById(id)));
        ioService.outputText(authorDtoList.toString());
        return authorDtoList;
    }
}
