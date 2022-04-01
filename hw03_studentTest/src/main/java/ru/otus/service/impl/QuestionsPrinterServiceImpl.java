package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.service.IOService;
import ru.otus.service.QuestionsPrinterService;

@Service
@RequiredArgsConstructor
public class QuestionsPrinterServiceImpl implements QuestionsPrinterService {

    private final IOService ioService;

    @Override
    public void printQuestion(Question question) {
        ioService.outputFormatText(createTemplate(question), question.getAnswerList().toArray());
    }

    private String createTemplate(Question question) {
        String template = question.getQuestion();
        for (Answer answer : question.getAnswerList()) {
            template += " %s";
        }
        template += "\n";
        return template;
    }
}
