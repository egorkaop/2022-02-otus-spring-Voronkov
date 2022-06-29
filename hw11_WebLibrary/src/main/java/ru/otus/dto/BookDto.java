package ru.otus.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookDto {
    private String id;
    @NotBlank(message = "{name-field-should-not-be-blank}")
    private String title;
    private List<AuthorDto> authors;
    private List<GenreDto> genres;

}
