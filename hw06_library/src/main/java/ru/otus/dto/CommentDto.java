package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private String text;
    private BookDto bookDto;

    @Override
    public String toString() {
        return "text='" + text + '\'' +
                ", bookDto=" + bookDto;
    }
}
