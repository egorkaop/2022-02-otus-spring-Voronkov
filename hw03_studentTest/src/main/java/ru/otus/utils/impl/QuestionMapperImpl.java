package ru.otus.utils.impl;

import org.springframework.stereotype.Component;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.dto.QuestionDto;
import ru.otus.utils.QuestionMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question mapDtoToQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestion(questionDto.getQuestion());
        String[] arr = questionDto.getAnswers().split(" ");
        List<Answer> answerList = new ArrayList<>();
        for (String str : arr) {
            Answer answer = new Answer();
            answer.setAnswer(str);
            if (str.equals(questionDto.getCorrectAnswer())) {
                answer.setCorrect(true);
            } else {
                answer.setCorrect(false);
            }
            answerList.add(answer);
        }
        question.setAnswerList(answerList);
        return question;
    }

    @Override
    public List<Question> mapListDtoToQuestion(List<QuestionDto> questionDtoList) {
        List<Question> questionList = new ArrayList<>();
        for (QuestionDto questionDto : questionDtoList) {
            questionList.add(mapDtoToQuestion(questionDto));
        }
        return questionList;
    }

}
