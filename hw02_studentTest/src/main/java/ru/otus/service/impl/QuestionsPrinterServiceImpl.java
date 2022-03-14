package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.service.IOService;
import ru.otus.service.QuestionsPrinterService;

@Service
@RequiredArgsConstructor
public class QuestionsPrinterServiceImpl implements QuestionsPrinterService {

    private final IOService ioService;

    @Override
    public void printQuestion(Question question) {
        ioService.outputText(question.toString());
    }
}
