package ru.otus.domain;

import com.opencsv.bean.CsvBindByPosition;

public class Question {

    @CsvBindByPosition(position = 0)
    private int index;
    @CsvBindByPosition(position = 1)
    private String question;
    @CsvBindByPosition(position = 2)
    private String firstAnswer;
    @CsvBindByPosition(position = 3)
    private String secondAnswer;

    public int getIndex() {
        return index;
    }

    public String getQuestion() {
        return question;
    }

    public String getFirstAnswer() {
        return firstAnswer;
    }

    public String getSecondAnswer() {
        return secondAnswer;
    }

    @Override
    public String toString() {
        return question + " " +
                firstAnswer + " " +
                secondAnswer;
    }
}
