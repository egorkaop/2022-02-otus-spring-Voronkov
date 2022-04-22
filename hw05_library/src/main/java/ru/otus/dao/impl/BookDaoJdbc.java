package ru.otus.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.BookDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exceptions.BookNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int count() {
        Integer count = namedParameterJdbcOperations.getJdbcOperations().queryForObject("select count(*) from books",Integer.class);
        return count == null?0:count;
    }

    @Override
    public Book getBookById(long id) {
        return namedParameterJdbcOperations.queryForObject(
                "select book.id as book_id, book.name as book_name ,book.author_id as bookau_id" +
                        ",author.name as author_name,author.surname as author_surname" +
                        ",genre.title as genre_title,book.genre_id as bookge_id from books as book" +
                        " left join authors as author on book.author_id=author.id"+
                        " left join genres as genre on book.genre_id=genre.id"+
                        " where book.id=:id", Map.of("id",id), new BookMapper());
    }

    @Override
    public List<Book> getAllBook() {
        return namedParameterJdbcOperations.query(
                "select book.id as book_id, book.name as book_name, book.author_id as bookau_id" +
                        ",author.name as author_name,author.surname as author_surname" +
                        ",genre.title as genre_title, book.genre_id as bookge_id from books as book" +
                        " left join authors as author on book.author_id=author.id"+
                        " left join genres as genre on book.genre_id=genre.id"
                        , new BookMapper());
    }

    @Override
    public void insertBook(Book book) {
        namedParameterJdbcOperations.update(
                "insert into books (name,author_id,genre_id) values (:name,:author_id,:genre_id)"
                ,Map.of("name",book.getTitle(),"author_id",book.getAuthor().getId(),"genre_id",book.getGenre().getId()));
    }

    @Override
    public void deleteBookById(long id) {
        int deletedRows=namedParameterJdbcOperations.update("delete from books where id=:id", Map.of("id", id));
        if(deletedRows==0){
            throw new BookNotFoundException("По заданному id книги не найдено");
        }
    }
    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("book_id");
            String name = rs.getString("book_name");
            Author author = new Author(rs.getLong("author_id"),rs.getString("author_name"),rs.getString("author_surname"));
            Genre genre = new Genre(rs.getLong("bookge_id"),rs.getString("genre_title"));
            return new Book(id,name,author,genre);
        }
    }
}
