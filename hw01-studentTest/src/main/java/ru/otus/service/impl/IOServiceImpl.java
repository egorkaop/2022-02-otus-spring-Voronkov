package ru.otus.service.impl;

import java.util.List;
import ru.otus.domain.Question;
import ru.otus.service.IOService;

public class IOServiceImpl implements IOService {

    @Override
    public void printAllOfQuestions(List<Question> list) {
        for (Question question : list) {
            System.out.println(question);
        }
    }
}
