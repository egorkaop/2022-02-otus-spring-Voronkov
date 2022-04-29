package ru.otus.domain;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString
@Getter
@Table(name = "comments")
public class Comment {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Book book;
    @Column(name = "text", nullable = false)
    private String text;
}
