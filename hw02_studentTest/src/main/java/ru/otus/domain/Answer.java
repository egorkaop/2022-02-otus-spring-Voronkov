package ru.otus.domain;

<<<<<<< HEAD
public class Answer {
    String text;
    boolean correct;

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }
=======
import java.util.List;

public class Answer {
    String answer;
    boolean correct;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        return answer;
    }
>>>>>>> origin/main
}
