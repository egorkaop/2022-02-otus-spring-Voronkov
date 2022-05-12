package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Long> {
    List<Genre> findByBook_Id(long id);

    @Modifying
    @Query("update Genre g set g.name=:name where g.id=:id")
    void updateGenreNameById(@Param("id") long id, @Param("name") String name);
}
