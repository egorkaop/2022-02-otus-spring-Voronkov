package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Modifying
    @Query("update Book b set b.title=:title where b.id=:id")
    void updateBookTitleById(@Param("id") long id, @Param("title") String title);
}
