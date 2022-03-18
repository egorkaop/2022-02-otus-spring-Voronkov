package ru.otus.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Person;
import ru.otus.domain.Question;
import ru.otus.service.IOService;
import ru.otus.service.PersonService;
import ru.otus.service.QuestionService;
import ru.otus.service.QuestionsPrinterService;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final QuestionsPrinterService questionsPrinterService;
    private final PersonService personService;
    private final IOService ioService;
    private final int successfulScore;
    private static final String FORMAT_OF_RESULT = "%s %s количество верных ответов:%s количество ответов для сдачи:%s \n";

    public QuestionServiceImpl(QuestionDao questionDao, QuestionsPrinterService questionsPrinterService
            , PersonService personService, IOService ioService, @Value("${successful.score}") int successfulScore) {
        this.questionDao = questionDao;
        this.questionsPrinterService = questionsPrinterService;
        this.personService = personService;
        this.ioService = ioService;
        this.successfulScore = successfulScore;
    }

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
            if (checkAnswer(getCorrectAnswer(question), getPersonAnswer())) {
                score++;
            }
        }
        printResult(person, score);
    }

    private String getCorrectAnswer(Question question) {
        String correctAnswer = "";
        for (Answer answer : question.getAnswerList()) {
            if (answer.isCorrect()) {
                correctAnswer = answer.getAnswer();
            }
        }
        return correctAnswer;
    }

    private String getPersonAnswer() {
        return ioService.inputText();
    }

    private boolean checkAnswer(String correctAnswer, String personAnswer) {
        return correctAnswer.equals(personAnswer);
    }

    private void printResult(Person person, int score) {
        ioService.outputFormatText(FORMAT_OF_RESULT, person.getFirstName(), person.getSurName(), score, successfulScore);
        if (score >= successfulScore) {
            ioService.outputText("Вы сдали");
        } else {
            ioService.outputText("Вы не сдали");
        }
    }

}
