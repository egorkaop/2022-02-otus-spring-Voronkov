package ru.otus.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookDto {
    private long id;
    private String title;
    private List<AuthorDto> authors;
    private List<GenreDto> genres;

    public BookDto(long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", authors=" + authors.toString() +
                ", genres=" + genres.toString();
    }
}
