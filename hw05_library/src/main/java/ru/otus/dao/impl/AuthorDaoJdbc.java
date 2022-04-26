package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;
import ru.otus.exceptions.AuthorNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;


    @Override
    public int count() {
        Integer count = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from authors", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public Author getAuthorByID(long id) {
        return namedParameterJdbcOperations.queryForObject("select id,name,surname from authors where id=:id"
                , Map.of("id", id), new AuthorMapper());
    }

    @Override
    public List<Author> getAllAuthor() {
        return namedParameterJdbcOperations.query("select id,name,surname from authors", new AuthorMapper());
    }

    @Override
    public void insertAuthor(Author author) {
        namedParameterJdbcOperations.update("insert into authors (name,surname) values (:name,:surname)"
                , Map.of("name", author.getName(), "surname", author.getSurname()));
    }

    @Override
    public void deleteAuthorById(long id) {
        int deletedRows = namedParameterJdbcOperations.update("delete from authors where id = :id", Map.of("id", id));
        if (deletedRows == 0) {
            throw new AuthorNotFoundException("По заданному id нет авторов");
        }
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(rs.getLong("id"), rs.getString("name"), rs.getString("surname"));
        }
    }
}
