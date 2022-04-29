package ru.otus.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "genres")
@ToString
@NamedEntityGraph(name = "genre-books-eg", attributeNodes = @NamedAttributeNode("books"))
public class Genre {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",nullable = false)
    private String name;
    @OneToMany
    @JoinColumn(name = "genre_id")
    private List<Book> books;

    public Genre(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }
}
