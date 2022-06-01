package ru.otus.utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Comment;
import ru.otus.dto.CommentDto;
import ru.otus.utils.CommentDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentDtoMapperImpl implements CommentDtoMapper {

    @Override
    public CommentDto convertCommentToDto(Comment comment) {
        String text = comment.getText();
        return new CommentDto(text);
    }

    @Override
    public List<CommentDto> convertListCommentsToDto(List<Comment> commentList) {
        return commentList.stream()
                .map(this::convertCommentToDto)
                .collect(Collectors.toList());
    }
}
