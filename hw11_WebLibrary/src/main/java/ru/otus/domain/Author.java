package ru.otus.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "author")
@ToString
public class Author {
    @Id
    private String id;
    private String name;
    private String surname;


    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
