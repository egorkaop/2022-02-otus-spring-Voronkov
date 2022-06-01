package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class AuthorDto {
    private long id;
    private String name;
    private String surname;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", surname='" + surname + '\'';
    }
}
