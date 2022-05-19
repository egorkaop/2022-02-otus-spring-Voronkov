package ru.otus.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.CommentDao;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.exceptions.CommentNotFoundException;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentDaoJpa implements CommentDao {
    private final EntityManager entityManager;

    public CommentDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(c) from Comment c", Long.class).getSingleResult();
    }

    @Override
    public Comment getCommentById(long id) {
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c where c.id=:id", Comment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }


    @Override
    public void insertComment(Comment comment) {
        if (comment.getId() <= 0) {
            entityManager.persist(comment);
        } else {
            entityManager.merge(comment);
        }
    }

    @Override
    public void deleteCommentById(long id) {
        Query query = entityManager.createQuery("delete from Comment c where c.id=:id");
        query.setParameter("id", id);
        int deletedRows = query.executeUpdate();
        if (deletedRows == 0) {
            throw new CommentNotFoundException("По заданному id комментария не найдено не найдено");
        }
    }

    @Override
    public void updateCommentTextById(long id, String text) {
        Query query = entityManager.createQuery("update Comment c set c.text=:text where c.id=:id");
        query.setParameter("text", text);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Comment> getCommentsByBookId(Book book) {
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c where c.book=:book",Comment.class);
        query.setParameter("book",book);
        return query.getResultList();
    }
}
