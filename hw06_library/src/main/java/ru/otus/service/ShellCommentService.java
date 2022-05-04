package ru.otus.service;

public interface ShellCommentService {
    void getCommentCount();

    void getCommentByID();

    void getAllComments();

    void insertComment();

    void deleteCommentById();

    void updateCommentTextById();
}
