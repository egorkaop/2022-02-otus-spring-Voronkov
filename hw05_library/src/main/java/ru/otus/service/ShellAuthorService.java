package ru.otus.service;

import ru.otus.domain.Author;

import java.util.List;

public interface ShellAuthorService {
    void getAuthorCount();
    void getAuthorByID();
    void getAllAuthors();
    void insertAuthor();
    void deleteAuthorById();

}
