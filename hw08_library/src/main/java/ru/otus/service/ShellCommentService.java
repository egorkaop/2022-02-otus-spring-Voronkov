package ru.otus.service;

public interface ShellCommentService {
    void getCommentCount();

    void getCommentByText();

    void insertComment();

    void deleteCommentByText();

    void updateCommentTextByText();

    void getAllCommentsByBookTitle();
}
