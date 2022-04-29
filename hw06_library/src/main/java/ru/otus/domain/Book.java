package ru.otus.domain;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title",nullable = false)
    private String title;

    public Book(String title) {
        this.title = title;
    }
}
