package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Entity
@Table(name = "authors")
@Getter
@AllArgsConstructor
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

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

}
