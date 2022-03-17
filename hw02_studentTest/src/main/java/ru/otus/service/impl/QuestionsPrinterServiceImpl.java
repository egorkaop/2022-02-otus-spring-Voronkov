package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.dto.QuestionDto;
import ru.otus.service.IOService;
import ru.otus.service.QuestionsPrinterService;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class QuestionsPrinterServiceImpl implements QuestionsPrinterService {

    private final IOService ioService;

    @Override
    public void printQuestion(QuestionDto question) {
        ioService.outputFormatText(createTemplate(question), question.getAnswerList().toArray());
    }

    private String createTemplate(QuestionDto questionDto){
        String template=questionDto.getQuestion();
        for(Answer answer:questionDto.getAnswerList()){
            template+=" %s";
        }
        template+="\n";
        return template;
    }
}
