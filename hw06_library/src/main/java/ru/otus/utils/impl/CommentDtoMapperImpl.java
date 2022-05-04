package ru.otus.utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Comment;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.utils.BookDtoMapper;
import ru.otus.utils.CommentDtoMapper;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentDtoMapperImpl implements CommentDtoMapper {
    private final BookDtoMapper bookDtoMapper;

    @Override
    public CommentDto convertCommentToDto(Comment comment) {
        String text = comment.getText();
        BookDto bookDto = bookDtoMapper.convertBookToDto(comment.getBook());
        return new CommentDto(text, bookDto);
    }

    @Override
    public List<CommentDto> convertListCommentsToDto(List<Comment> commentList) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentDtoList.add(convertCommentToDto(comment));
        }
        return commentDtoList;
    }
}
