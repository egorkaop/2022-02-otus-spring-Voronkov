package ru.otus.dto;

import ru.otus.domain.Answer;

import java.util.List;

public class QuestionDto {
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
        return "QuestionDto{" +
                "question='" + question + '\'' +
                ", answerList=" + answerList +
                '}';
    }
}
