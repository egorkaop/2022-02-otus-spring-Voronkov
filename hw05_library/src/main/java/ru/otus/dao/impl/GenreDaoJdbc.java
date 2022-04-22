package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Genre;
import ru.otus.exceptions.GenreNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int count() {
        Integer count = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from genres",Integer.class);
        return count == null?0:count;
    }

    @Override
    public Genre getGenreById(long id) {
        return namedParameterJdbcOperations.queryForObject("select id,title from genres where id=:id"
                , Map.of("id",id), new GenreMapper());
    }

    @Override
    public List<Genre> getAllGenre() {
        return namedParameterJdbcOperations.query("select id,title from genres",new GenreMapper());
    }

    @Override
    public void insertGenre(Genre genre) {
        namedParameterJdbcOperations.update("insert into genres (title) values (:title)"
                ,Map.of("title",genre.getName()));
    }

    @Override
    public void deleteGenreById(long id) {
        int deletedRows = namedParameterJdbcOperations.update("delete from genres where id = :id",Map.of("id",id));
        if (deletedRows==0){
            throw new GenreNotFoundException("По заданному id нет жанра");
        }
    }

    private static class GenreMapper implements RowMapper<Genre>{

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre(rs.getLong("id"),rs.getString("title"));
        }
    }
}
