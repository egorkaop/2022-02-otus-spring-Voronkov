package ru.otus.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.CommentDao;
import ru.otus.domain.Comment;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class CommentDaoJpa implements CommentDao {
    private final EntityManager entityManager;

    public CommentDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(c) from Comment c",Long.class).getSingleResult();
    }

    @Override
    public Comment getCommentById(long id) {
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c where c.id=:id",Comment.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public List<Comment> getAllComment() {
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c",Comment.class);
        return query.getResultList();
    }

    @Override
    public void insertComment(Comment comment) {
        if (comment.getId()<=0){
            entityManager.persist(comment);
        }
        else {
            entityManager.merge(comment);
        }
    }

    @Override
    public void deleteCommentById(long id) {
        Query query = entityManager.createQuery("delete from Comment c where c.id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public void updateCommentTextById(long id, String text) {
        Query query = entityManager.createQuery("update Comment c set c.text=:text where c.id=:id");
        query.setParameter("text",text);
        query.setParameter("id",id);
        query.executeUpdate();
    }
}
