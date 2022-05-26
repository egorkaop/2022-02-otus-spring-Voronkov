package ru.otus.utils;

import ru.otus.domain.Comment;
import ru.otus.dto.CommentDto;

import java.util.List;

public interface CommentDtoMapper {
    CommentDto convertCommentToDto(Comment comment);

    List<CommentDto> convertListCommentsToDto(List<Comment> commentList);
}
