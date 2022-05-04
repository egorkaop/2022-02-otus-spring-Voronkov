package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;
import ru.otus.dto.AuthorDto;
import ru.otus.exceptions.AuthorNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.ShellAuthorService;
import ru.otus.utils.AuthorDtoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellAuthorServiceImpl implements ShellAuthorService {
    private final AuthorDao authorDao;
    private final IOService ioService;
    private final AuthorDtoMapper authorDtoMapper;


    @Override
    public void getAuthorCount() {
        ioService.outputText("Количество авторов: " + authorDao.count());
    }

    @Override
    public void getAuthorByID() {
        ioService.outputText("Введите id автора");
        long id = Long.parseLong(ioService.inputText());
        try {
            AuthorDto authorDto = authorDtoMapper.convertAuthorToDto(authorDao.getAuthorById(id));
            ioService.outputText(authorDto.toString());
        } catch (Exception e) {
            throw new AuthorNotFoundException("По задданому id автор не найден");
        }
    }

    @Override
    @Transactional
    public void getAllAuthors() {
        List<AuthorDto> authorDtoList = authorDtoMapper.convertListAuthorsToDto(authorDao.getAllAuthor());
        ioService.outputText(authorDtoList.toString());
    }

    @Override
    @Transactional
    public void insertAuthor() {
        ioService.outputText("Введите имя автора");
        String name = ioService.inputText();
        ioService.outputText("Введите фамилию автора");
        String surname = ioService.inputText();
        authorDao.insertAuthor(new Author(name, surname));
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

    @Override
    public void updateAuthorNameById() {
        ioService.outputText("Введите id автора для замены");
        long id = Long.parseLong(ioService.inputText());
        ioService.outputText("Введите новое имя автора");
        String name = ioService.inputText();
        authorDao.updateAuthorNameById(id, name);
    }
}
