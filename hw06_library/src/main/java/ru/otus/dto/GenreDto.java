package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GenreDto {
    private String name;
    private List<BookDto> bookDtoList;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", bookDtoList=" + bookDtoList;
    }
}
