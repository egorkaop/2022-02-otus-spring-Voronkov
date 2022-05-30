package ru.otus.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByText(String text);

    void deleteByText(String text);

    void deleteByBook(Book book);

    List<Comment> findByBook(Book book);

}
