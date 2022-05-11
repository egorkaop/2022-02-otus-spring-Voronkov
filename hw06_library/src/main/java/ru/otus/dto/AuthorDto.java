package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorDto {
    private String name;
    private String surname;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", surname='" + surname + '\'';
    }
}
