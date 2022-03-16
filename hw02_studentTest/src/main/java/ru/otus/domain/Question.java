package ru.otus.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import lombok.Getter;

@Getter
public class Question {

    @CsvBindByPosition(position = 0)
    private int index;
    @CsvBindByPosition(position = 1)
    private String question;
    @CsvBindByPosition(position = 2)
    private String answers;
    @CsvBindByPosition(position = 3)
    private String correctAnswer;

    @Override
    public String toString() {
        return question + " " +
                answers;
    }
}
