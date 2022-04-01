package ru.otus.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Question {

    String question;
    List<Answer> answerList;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return question + answerList;
    }
}
