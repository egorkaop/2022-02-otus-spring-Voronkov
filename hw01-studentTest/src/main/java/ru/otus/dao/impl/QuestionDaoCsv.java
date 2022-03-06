package ru.otus.dao.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;
import ru.otus.exceptions.FileNotExist;

@RequiredArgsConstructor
public class QuestionDaoCsv implements QuestionDao {

    private final String csvFileName;

    public List<Question> getListOfQuestions() {
        List<Question> questionList = null;
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(csvFileName)) {
            questionList = new CsvToBeanBuilder(new InputStreamReader(inputStream))
                    .withType(Question.class).build().parse();
        } catch (IOException e) {
            throw new FileNotExist("file not exist");
        }
        return questionList;
    }
}
