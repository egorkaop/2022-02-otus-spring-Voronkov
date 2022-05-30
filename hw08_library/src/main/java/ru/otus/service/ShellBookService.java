package ru.otus.service;

public interface ShellBookService {
    void getBookCount();

    void getBookByTitle();

    void getAllBooks();

    void insertBook();

    void deleteBookByTitle();

    void updateBookTitleByTitle();
}
