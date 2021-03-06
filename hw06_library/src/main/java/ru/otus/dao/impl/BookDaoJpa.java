package ru.otus.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookDao;
import ru.otus.domain.Book;
import ru.otus.exceptions.BookNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class BookDaoJpa implements BookDao {
    private final EntityManager entityManager;

    public BookDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(b) from Book b", Long.class).getSingleResult();
    }

    @Override
    public Book getBookById(long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> getAllBook() {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public void insertBook(Book book) {
        if (book.getId() <= 0) {
            entityManager.persist(book);
        } else {
            entityManager.merge(book);
        }
    }

    @Override
    public void deleteBookById(long id) {
        Query query = entityManager.createQuery("delete from Book b where b.id=:id");
        query.setParameter("id", id);
        int deletedRows = query.executeUpdate();
        if (deletedRows == 0) {
            throw new BookNotFoundException("По заданному id книги не найдено");
        }
    }

    @Override
    public void updateBookTitleById(long id, String title) {
        Query query = entityManager.createQuery("update Book b set b.title=:title where b.id=:id");
        query.setParameter("title", title);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
