package ru.otus.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AuthorDto {
    private String name;
    private String surname;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", surname='" + surname + '\'';
    }
}
