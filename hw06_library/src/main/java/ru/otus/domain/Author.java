package ru.otus.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Entity
@Table(name = "authors")
@ToString
@Getter
@NamedEntityGraph(name = "author-books-eg", attributeNodes = @NamedAttributeNode("books"))
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "surname",nullable = false)
    private String surname;
    @OneToMany(targetEntity = Book.class)
    @JoinColumn(name = "author_id")
    private List<Book> books;

    public Author(String name, String surname, List<Book> books) {
        this.name = name;
        this.surname = surname;
        this.books = books;
    }
}
