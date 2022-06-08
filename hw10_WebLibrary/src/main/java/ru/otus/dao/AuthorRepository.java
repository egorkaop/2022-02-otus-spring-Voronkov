package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Author;
import ru.otus.domain.Book;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    List<Author> findAuthorByBookListContains(Book book);
    @Modifying
    @Query("update Author a set a.name=:name where a.id=:id")
    void updateAuthorNameById(@Param("id") long id, @Param("name") String name);
}
