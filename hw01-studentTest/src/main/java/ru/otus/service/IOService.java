package ru.otus.service;

import java.util.List;
import ru.otus.domain.Question;

public interface IOService {

    void printAllOfQuestions(List<Question> list);

}
