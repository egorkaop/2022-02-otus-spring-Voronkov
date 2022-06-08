package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBook_Id(long id);
    @Modifying
    @Query("update Comment c set c.text=:text where c.id=:id")
    void updateCommentTextById(@Param("id") long id, @Param("text") String text);
}
