package ru.otus.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
    @Column(name = "text", nullable = false)
    private String text;

    public Comment(Book book, String text) {
        this.book = book;
        this.text = text;
    }
}
