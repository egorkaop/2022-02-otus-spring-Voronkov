package ru.otus.dao;

import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;

public interface CommentDao {
    long count();

    Comment getCommentById(long id);

    void insertComment(Comment comment);

    void deleteCommentById(long id);

    void updateCommentTextById(long id, String text);

    List<Comment> getCommentsByBookId(Book book);
}
