package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenreDto {
    private String name;

    @Override
    public String toString() {
        return "name='" + name;
    }
}
