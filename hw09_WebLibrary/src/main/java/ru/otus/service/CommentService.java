package ru.otus.service;

import ru.otus.dto.CommentDto;

import java.util.List;

public interface CommentService {
    long getCommentCount();

    CommentDto getCommentByID(long id);

    void insertComment();

    void deleteCommentById();

    void updateCommentTextById();

    List<CommentDto> getAllCommentsByBookId();
}
