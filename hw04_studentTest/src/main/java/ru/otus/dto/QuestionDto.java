package ru.otus.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class QuestionDto {
    @CsvBindByName
    private int id;
    @CsvBindByName
    private String question;
    @CsvBindByName
    private String answers;
    @CsvBindByName
    private String correctAnswer;


}
