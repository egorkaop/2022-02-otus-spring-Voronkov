package ru.otus.utils;

import ru.otus.domain.Question;
import ru.otus.dto.QuestionDto;

import java.util.List;

public interface MappingUtilsQuestion {
    QuestionDto mapToQuestionDto(Question question);
    List<QuestionDto> mapToAllQuestionDto(List<Question> questionList);
}
