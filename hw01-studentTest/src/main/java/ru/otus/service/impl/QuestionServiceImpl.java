package ru.otus.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;
import ru.otus.exceptions.EmptyFileException;
import ru.otus.service.IOService;
import ru.otus.service.QuestionService;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final IOService ioService;

    private void askAllQuestion(List<Question> questionList) {
        if (questionList.isEmpty()) {
            throw new EmptyFileException("file is empty");
        }
        ioService.printAllOfQuestions(questionList);
    }

    @Override
    public void startTesting() {
        askAllQuestion(questionDao.getListOfQuestions());
    }


}
