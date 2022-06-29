package ru.otus.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "genre")
public class Genre {
    @Id
    private String id;

    private String name;


    public Genre(String name) {
        this.name = name;
    }
}
