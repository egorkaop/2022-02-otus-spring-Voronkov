package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private String text;

    @Override
    public String toString() {
        return "text='" + text;
    }
}
