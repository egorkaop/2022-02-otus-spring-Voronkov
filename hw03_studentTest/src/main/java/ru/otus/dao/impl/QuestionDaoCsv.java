package ru.otus.dao.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Repository;
import ru.otus.config.CsvFileConfig;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;
import ru.otus.dto.QuestionDto;
import ru.otus.exceptions.FileNotExistException;
import ru.otus.utils.QuestionMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao {
    private final CsvFileConfig csvFileConfig;
    private final QuestionMapper mappingUtilsQuestion;

    public QuestionDaoCsv(CsvFileConfig csvFileConfig, QuestionMapper mappingUtilsQuestion) {
        this.csvFileConfig = csvFileConfig;
        this.mappingUtilsQuestion = mappingUtilsQuestion;
    }

    public List<Question> getListOfQuestions() {
        List<QuestionDto> questionDtoList = null;
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(csvFileConfig.getFileName())) {
            questionDtoList = new CsvToBeanBuilder<QuestionDto>(new InputStreamReader(inputStream))
                    .withType(QuestionDto.class).build().parse();
        } catch (IOException e) {
            throw new FileNotExistException("file not exist");
        }

        return mappingUtilsQuestion.mapListDtoToQuestion(questionDtoList);
    }
}
