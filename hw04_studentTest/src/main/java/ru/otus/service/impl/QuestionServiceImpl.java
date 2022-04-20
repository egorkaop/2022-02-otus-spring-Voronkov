package ru.otus.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.CsvFileConfig;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Person;
import ru.otus.domain.Question;
import ru.otus.exceptions.WrongInputException;
import ru.otus.service.*;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final QuestionsPrinterService questionsPrinterService;
    private final PersonService personService;
    private final IOService ioService;
    private final int successfulScore;
    private static final String FORMAT_OF_RESULT = "%s %s %s:%s %s:%s \n";
    private final LocaleService localeService;
    private final LanguageService languageService;

    public QuestionServiceImpl(QuestionDao questionDao, QuestionsPrinterService questionsPrinterService
            , PersonService personService, IOService ioService, CsvFileConfig csvFileConfig
            , MessageSource messageSource, LocaleService localeService, LanguageService languageService) {
        this.questionDao = questionDao;
        this.questionsPrinterService = questionsPrinterService;
        this.personService = personService;
        this.ioService = ioService;
        this.successfulScore = csvFileConfig.getScore();
        this.localeService = localeService;
        this.languageService = languageService;
    }

    @Override
    public void startTesting() {
        try {
            chooseLanguage();
        } catch (WrongInputException e) {
            System.out.println("wrong input");
        }
        askAllQuestion(questionDao.getListOfQuestions(), personService.getPerson());
    }

    private void chooseLanguage() {
        ioService.outputText("Choose your language: ru en");
        languageService.checkAndSetLanguage(ioService.inputText());
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
        ioService.outputFormatText(FORMAT_OF_RESULT, person.getFirstName(), person.getSurName(),
                localeService.localizeText("strings.score"), score,
                localeService.localizeText("strings.passing"), successfulScore);
        if (score >= successfulScore) {
            ioService.outputText(localeService.localizeText("strings.successfully"));
        } else {
            ioService.outputText(localeService.localizeText("strings.failed"));
        }
    }

}
