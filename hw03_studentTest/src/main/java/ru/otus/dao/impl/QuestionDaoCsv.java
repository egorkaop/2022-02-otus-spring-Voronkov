package ru.otus.dao.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Repository;
import ru.otus.config.CsvFileConfig;
import ru.otus.config.LocaleConfig;
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
    private final LocaleConfig localeConfig;
    private final static String FORMAT_OF_FILENAME = "%s_%s.csv";

    public QuestionDaoCsv(CsvFileConfig csvFileConfig, QuestionMapper mappingUtilsQuestion, LocaleConfig localeConfig) {
        this.csvFileConfig = csvFileConfig;
        this.mappingUtilsQuestion = mappingUtilsQuestion;
        this.localeConfig = localeConfig;
    }

    public List<Question> getListOfQuestions() {
        List<QuestionDto> questionDtoList = null;
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(formatFileName(csvFileConfig.getDefaultFileName()))) {
            questionDtoList = new CsvToBeanBuilder<QuestionDto>(new InputStreamReader(inputStream))
                    .withType(QuestionDto.class).build().parse();
        } catch (IOException e) {
            throw new FileNotExistException("file not exist");
        }

        return mappingUtilsQuestion.mapListDtoToQuestion(questionDtoList);
    }

    private String formatFileName(String defaultFileName) {
        return String.format(FORMAT_OF_FILENAME, defaultFileName, localeConfig.getLocale());
    }
}
