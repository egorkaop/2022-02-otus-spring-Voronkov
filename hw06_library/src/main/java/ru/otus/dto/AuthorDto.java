package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthorDto {
    private String name;
    private String surname;
    private List<BookDto> bookDtoList;

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", bookDtoList=" + bookDtoList;
    }
}
