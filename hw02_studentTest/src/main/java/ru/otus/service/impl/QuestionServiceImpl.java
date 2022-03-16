package ru.otus.service.impl;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Person;
import ru.otus.domain.Question;
import ru.otus.service.IOService;
import ru.otus.service.PersonService;
import ru.otus.service.QuestionService;
import ru.otus.service.QuestionsPrinterService;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final QuestionsPrinterService questionsPrinterService;
    private final PersonService personService;
    private final IOService ioService;
    @Value("${successful.score}")
    private final int successfulScore;
    private static final String FORMAT_OF_RESULT="%s %s количество верных ответов:%s количество ответов для сдачи:%s \n";

    @Override
    public void startTesting() {

        askAllQuestion(questionDao.getListOfQuestions(), personService.getPerson());
    }

    private void askAllQuestion(List<Question> questionList, Person person) {
        int score = 0;
        if (questionList.isEmpty()) {
            ioService.outputText("No questions found");
        }
        for (Question question : questionList) {
            questionsPrinterService.printQuestion(question);
            if (checkAnswer(question, getAnswer())) {
                score++;
            }
        }
        printResult(person, score);
    }

    private String getAnswer() {
        return ioService.inputText();
    }

    private boolean checkAnswer(Question question, String personAnswer) {
        return question.getCorrectAnswer().equals(personAnswer);
    }

    private void printResult(Person person, int score) {
        ioService.outputFormatText(FORMAT_OF_RESULT,person.getFirstName(),person.getSurName(),score,successfulScore);
        if (score >= successfulScore) {
            ioService.outputText("Вы сдали");
        } else {
            ioService.outputText("Вы не сдали");
        }
    }

}
