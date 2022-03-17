package ru.otus.dao.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;
import ru.otus.exceptions.EmptyFileException;
import ru.otus.exceptions.FileNotExistException;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionDaoCsv implements QuestionDao {
    @Value("${csv.file.name}")
    private final String csvFileName;


    public List<Question> getListOfQuestions() {
        List<Question> questionList = null;
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(csvFileName)) {
            questionList = new CsvToBeanBuilder<Question>(new InputStreamReader(inputStream))
                    .withType(Question.class).build().parse();
        } catch (IOException e) {
            throw new FileNotExistException("file not exist");
        }
        return questionList;
    }
}
