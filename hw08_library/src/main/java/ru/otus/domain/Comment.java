package ru.otus.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "comment")
public class Comment {
    @Id
    private String id;

    private String text;
    @DBRef
    private Book book;

    public Comment(Book book, String text) {
        this.book = book;
        this.text = text;
    }
}
