package ru.otus.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
@NamedEntityGraph(name = "comment-books-eg", attributeNodes = @NamedAttributeNode("book"))
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;
    @Column(name = "text", nullable = false)
    private String text;

    public Comment(Book book, String text) {
        this.book = book;
        this.text = text;
    }
}
