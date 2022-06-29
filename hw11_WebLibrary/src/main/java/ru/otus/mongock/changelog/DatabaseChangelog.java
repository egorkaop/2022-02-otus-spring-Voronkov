package ru.otus.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.dao.AuthorRepository;
import ru.otus.dao.BookRepository;
import ru.otus.dao.CommentRepository;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@ChangeLog
public class DatabaseChangelog {
    private final List<Author> authorList = new ArrayList<>();
    private final List<Genre> genreList = new ArrayList<>();
    private final List<Book> bookList = new ArrayList<>();
    private final List<Comment> commentList = new ArrayList<>();
    private final String AUTHOR_NAME_TEMPLATE = "egor";
    private final String AUTHOR_SURNAME_TEMPLATE = "voronkov";
    private final String GENRE_NAME_TEMPLATE = "genre";
    private final String BOOK_TITLE_TEMPLATE = "bookTitle";
    private final String COMMENT_TEXT_TEMPLATE = "comment";

    @ChangeSet(order = "001", id = "dropDb", author = "egorkaop", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthor", author = "egorkaop")
    public void insertAuthor(AuthorRepository authorRepository) {
        IntStream.range(0, 10).forEach(i -> authorList.add(new Author(AUTHOR_NAME_TEMPLATE + i, AUTHOR_SURNAME_TEMPLATE + i)));
        authorRepository.saveAll(authorList).subscribe();
    }

    @ChangeSet(order = "003", id = "insertGenre", author = "egorkaop")
    public void insertGenre(GenreRepository genreRepository) {
        IntStream.range(0, 10)
                .forEach(i -> genreList.add(new Genre(GENRE_NAME_TEMPLATE + i)));
        genreRepository.saveAll(genreList).subscribe();
    }

    @ChangeSet(order = "004", id = "insertBook", author = "egorkaop")
    public void insertBook(BookRepository bookRepository) {
        IntStream.range(0, 10)
                .forEach(i -> bookList.add(new Book(
                        BOOK_TITLE_TEMPLATE + i
                        , List.of(authorList.get(i))
                        , List.of(genreList.get(i)))));
        bookRepository.saveAll(bookList).subscribe();
    }


}
