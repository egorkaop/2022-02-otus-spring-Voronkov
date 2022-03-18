package ru.otus.service;

import ru.otus.domain.Question;
import ru.otus.dto.QuestionDto;

public interface QuestionsPrinterService {

    void printQuestion(Question question);

}
