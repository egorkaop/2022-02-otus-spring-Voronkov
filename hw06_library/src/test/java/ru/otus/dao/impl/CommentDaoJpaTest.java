package ru.otus.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("дао комментариев должно ")
@DataJpaTest
@Import(CommentDaoJpa.class)
class CommentDaoJpaTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CommentDaoJpa commentDaoJpa;

    private static final long EXPECTED_COUNT_OF_COMMENTS = 2;
    private static final long EXISTING_COMMENT_ID = 1;
    private static final String EXISTING_COMMENT_TEXT = "Хорошая книга";

    @Test
    @DisplayName("правильно возвращать количество комментариев")
    void shouldReturnCorrectCountOfComment() {
        long actualCount = commentDaoJpa.count();
        assertThat(actualCount).isEqualTo(EXPECTED_COUNT_OF_COMMENTS);
    }

    @Test
    @DisplayName("правильно возвращать комментарий по id")
    void shouldReturnCorrectCommentById() {
        Comment expectedComment = testEntityManager.find(Comment.class, EXISTING_COMMENT_ID);
        Comment actualComment = commentDaoJpa.getCommentById(EXISTING_COMMENT_ID);
        assertThat(actualComment).isEqualTo(expectedComment);
    }


    @Test
    @DisplayName("удалять комментарий")
    void shouldDeleteComment() {
        Comment comment = testEntityManager.find(Comment.class, EXISTING_COMMENT_ID);
        assertThat(comment).isNotNull();
        testEntityManager.detach(comment);
        commentDaoJpa.deleteCommentById(EXISTING_COMMENT_ID);
        Comment deletedComment = testEntityManager.find(Comment.class, EXISTING_COMMENT_ID);
        assertThat(deletedComment).isNull();
    }

    @Test
    @DisplayName("обновлять текст комментария")
    void shouldCorrectUpdateCommentTitleByID() {
        Comment oldComment = testEntityManager.find(Comment.class, EXISTING_COMMENT_ID);
        String oldText = oldComment.getText();
        assertThat(oldText).isEqualTo(EXISTING_COMMENT_TEXT);
        commentDaoJpa.updateCommentTextById(EXISTING_COMMENT_ID, "good");
        testEntityManager.detach(oldComment);
        String newText = testEntityManager.find(Comment.class, EXISTING_COMMENT_ID).getText();
        assertThat(newText).isEqualTo("good");
    }
}