package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookDto {
    private String title;
    private List<AuthorDto> authors;
    private List<GenreDto> genres;

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", authors=" + authors.toString() +
                ", genres=" + genres.toString();
    }
}
