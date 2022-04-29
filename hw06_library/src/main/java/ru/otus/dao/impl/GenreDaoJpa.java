package ru.otus.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Genre;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class GenreDaoJpa implements GenreDao {
    private final EntityManager entityManager;

    public GenreDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(g) from Genre g",Long.class).getSingleResult();
    }

    @Override
    public Genre getGenreById(long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("genre-books-eg");
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g where g.id=:id",Genre.class);
        query.setParameter("id",id);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getSingleResult();
    }

    @Override
    public List<Genre> getAllGenre() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("genre-books-eg");
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g",Genre.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public void insertGenre(Genre genre) {
        if (genre.getId()<=0){
            entityManager.persist(genre);
        }
        else {
            entityManager.merge(genre);
        }
    }

    @Override
    public void deleteGenreById(long id) {
        Query query = entityManager.createQuery("delete from Genre g where g.id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public void updateGenreNameById(long id, String name) {
        Query query = entityManager.createQuery("update Genre g set g.name=:name where g.id=:id");
        query.setParameter("name",name);
        query.setParameter("id",id);
        query.executeUpdate();
    }
}