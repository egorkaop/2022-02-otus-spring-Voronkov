package ru.otus.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookInsertDto {
    private String title;
    private List<Long> authors;
    private List<Long> genres;
}
