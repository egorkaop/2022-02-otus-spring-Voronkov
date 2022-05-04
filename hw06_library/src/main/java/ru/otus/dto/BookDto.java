package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.domain.Author;
import ru.otus.domain.Genre;

@Data
@AllArgsConstructor
public class BookDto {
    private String title;
    private Author author;
    private Genre genre;

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", author=" + author.getName() + " " + author.getSurname() +
                ", genre=" + genre.getName();
    }
}
