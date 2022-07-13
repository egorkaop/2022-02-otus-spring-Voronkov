package ru.otus.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookInsertDto {
    private String title;
    private List<String> authors;
    private List<String> genres;
}
