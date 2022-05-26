package ru.otus.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findByTitle(String title);

    void deleteByTitle(String title);

    void deleteByAuthorsContains(List<Author> authors);

    void deleteByGenresContains(List<Genre> genres);
}
