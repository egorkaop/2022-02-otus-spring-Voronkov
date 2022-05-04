package ru.otus.domain;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title",nullable = false)
    private String title;
    @Column(name = "author_id",nullable = false)
    private long author_id;
    @Column(name = "genre_id", nullable = false)
    private long genre_id;

    public Book(String title, long author_id, long genre_id) {
        this.title = title;
        this.author_id = author_id;
        this.genre_id = genre_id;
    }
}
