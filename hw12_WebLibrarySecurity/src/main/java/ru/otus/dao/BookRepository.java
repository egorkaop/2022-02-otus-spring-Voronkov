package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByAuthorsContains(Author author);
    List<Book> findAllByGenresContains(Genre genre);
    @Modifying
    @Query("update Book b set b.title=:title where b.id=:id")
    void updateBookTitleById(@Param("id") long id, @Param("title") String title);
}
