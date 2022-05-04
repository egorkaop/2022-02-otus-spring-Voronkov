package ru.otus.dao.impl;

import org.springframework.stereotype.Repository;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;
import ru.otus.exceptions.AuthorNotFoundException;

import javax.persistence.*;
import java.util.List;

@Repository
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(a) from Author a", Long.class).getSingleResult();
    }

    @Override
    public Author getAuthorById(long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("author-books-eg");
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a where a.id=:id", Author.class);
        query.setParameter("id", id);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAllAuthor() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("author-books-eg");
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a", Author.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public void insertAuthor(Author author) {
        if (author.getId() == 0) {
            entityManager.persist(author);
        } else {
            entityManager.merge(author);
        }
    }

    @Override
    public void deleteAuthorById(long id) {
        Query query = entityManager.createQuery("delete from Author a where a.id=:id");
        query.setParameter("id", id);
        int deletedRows = query.executeUpdate();
        if (deletedRows == 0) {
            throw new AuthorNotFoundException("По данному id автора не найдено");
        }
    }

    @Override
    public void updateAuthorNameById(long id, String name) {
        Query query = entityManager.createQuery("update Author a set a.name=:name where a.id=:id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
