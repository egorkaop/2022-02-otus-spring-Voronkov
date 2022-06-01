package ru.otus.service;

import ru.otus.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    long getAuthorCount();

    AuthorDto getAuthorByID(long id);

    List<AuthorDto> getAllAuthors();

    void insertAuthor();

    void deleteAuthorById();

    void updateAuthorNameById();

    List<AuthorDto> getAllAuthorsByBookId();


}
