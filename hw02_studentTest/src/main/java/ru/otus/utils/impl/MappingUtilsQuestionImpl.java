package ru.otus.utils.impl;

import org.springframework.stereotype.Service;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.dto.QuestionDto;
import ru.otus.utils.MappingUtilsQuestion;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingUtilsQuestionImpl implements MappingUtilsQuestion {
    @Override
    public QuestionDto mapToQuestionDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestion(question.getQuestion());
        String[] arr = question.getAnswers().split(" ");
        List<Answer> answerList = new ArrayList<>();
        for(String str:arr){
            Answer answer = new Answer();
            answer.setAnswer(str);
            if(str.equals(question.getCorrectAnswer())){
                answer.setCorrect(true);
            }
            else {
                answer.setCorrect(false);
            }
            answerList.add(answer);
        }
        questionDto.setAnswerList(answerList);
        return questionDto;
    }

    @Override
    public List<QuestionDto> mapToAllQuestionDto(List<Question> questionList) {
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(Question question:questionList){
            questionDtoList.add(mapToQuestionDto(question));
        }
        return questionDtoList;
    }
}
