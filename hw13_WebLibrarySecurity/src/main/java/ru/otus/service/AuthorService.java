package ru.otus.service;

import ru.otus.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthors();


    void deleteAuthorById(long id);



}
