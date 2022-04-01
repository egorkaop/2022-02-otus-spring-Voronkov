package ru.otus.utils;

import ru.otus.domain.Question;
import ru.otus.dto.QuestionDto;

import java.util.List;

public interface QuestionMapper {
    Question mapDtoToQuestion(QuestionDto questionDto);

    List<Question> mapListDtoToQuestion(List<QuestionDto> questionDtoList);
}
