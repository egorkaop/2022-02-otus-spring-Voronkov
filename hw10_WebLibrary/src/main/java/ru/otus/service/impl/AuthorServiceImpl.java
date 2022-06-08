package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorRepository;
import ru.otus.dto.AuthorDto;
import ru.otus.exceptions.AuthorNotFoundException;
import ru.otus.service.AuthorService;
import ru.otus.utils.AuthorDtoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDtoMapper authorDtoMapper;
    private final AuthorRepository authorRepository;


    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        return authorDtoMapper.convertListAuthorsToDto(authorRepository.findAll());
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

}
