package ru.otus.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Person;
import ru.otus.domain.Question;
import ru.otus.service.IOService;
import ru.otus.service.PersonService;
import ru.otus.service.QuestionService;
import ru.otus.service.QuestionsPrinterService;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final QuestionsPrinterService questionsPrinterService;
    private final PersonService personService;
    private final IOService ioService;
    @Value("${successful.score}")
    private int successfulScore;

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
        ioService.closeInput();
    }

    private String getAnswer() {
        return ioService.inputText();
    }

    private boolean checkAnswer(Question question, String personAnswer) {
        return question.getCorrectAnswer().equals(personAnswer);
    }

    private void printResult(Person person, int score) {
        ioService.outputText(person.getFirstName() + " " + person.getSurName()
                + " количество верных ответов:" + score
                + " количество ответов для сдачи:" + successfulScore);
        if (score >= successfulScore) {
            ioService.outputText("Вы сдали");
        } else {
            ioService.outputText("Вы не сдали");
        }
    }

}
