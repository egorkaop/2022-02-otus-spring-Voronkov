package ru.otus.service;

public interface ShellCommentService {
    void getCommentCount();

    void getCommentByID();

    void insertComment();

    void deleteCommentById();

    void updateCommentTextById();

    void getAllCommentsByBookId();
}
