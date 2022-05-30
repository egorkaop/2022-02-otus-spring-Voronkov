package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorRepository;
import ru.otus.dao.BookRepository;
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
    private final IOService ioService;
    private final AuthorDtoMapper authorDtoMapper;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    @Override
    public void getAuthorCount() {
        ioService.outputText("Количество авторов: " + authorRepository.count());
    }

    @Override
    public void getAuthorByName() {
        ioService.outputText("Введите имя автора");
        String authorName = ioService.inputText();
        try {
            List<AuthorDto> authorDto = authorDtoMapper.convertListAuthorsToDto(authorRepository.findByName(authorName));
            ioService.outputText(authorDto.toString());
        } catch (Exception e) {
            throw new AuthorNotFoundException("По задданому имени автор не найден");
        }
    }

    @Override
    public void getAllAuthors() {
        List<AuthorDto> authorDtoList = authorDtoMapper.convertListAuthorsToDto(authorRepository.findAll());
        ioService.outputText(authorDtoList.toString());
    }

    @Override
    public void insertAuthor() {
        ioService.outputText("Введите имя автора");
        String name = ioService.inputText();
        ioService.outputText("Введите фамилию автора");
        String surname = ioService.inputText();
        authorRepository.save(new Author(name, surname));
    }

    @Override
    public void deleteAuthorByName() {
        ioService.outputText("Введите имя автора для удаления");
        String authorName = ioService.inputText();
        try {
            authorRepository.deleteByName(authorName);
        } catch (Exception e) {
            throw new AuthorNotFoundException("По задданому имени автор не найден");
        }
    }

    @Override
    public void updateAuthorNameByName() {
        ioService.outputText("Введите имя автора для замены");
        String oldAuthorName = ioService.inputText();
        ioService.outputText("Введите новое имя автора");
        String newAuthorName = ioService.inputText();
        authorRepository.findByName(oldAuthorName).stream()
                .peek(x -> x.setName(newAuthorName))
                .forEach(authorRepository::save);
    }
}
