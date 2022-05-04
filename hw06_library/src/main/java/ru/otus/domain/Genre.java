package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "genres")
@NamedEntityGraph(name = "genre-books-eg", attributeNodes = @NamedAttributeNode("books"))
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany
    @JoinColumn(name = "genre_id")
    private List<Book> books;

    public Genre(String name) {
        this.name = name;
    }
}
