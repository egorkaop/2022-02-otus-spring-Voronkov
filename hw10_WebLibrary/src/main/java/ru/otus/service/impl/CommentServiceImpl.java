package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookRepository;
import ru.otus.dao.CommentRepository;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.dto.CommentDto;
import ru.otus.exceptions.CommentNotFoundException;
import ru.otus.service.CommentService;
import ru.otus.service.IOService;
import ru.otus.utils.CommentDtoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final IOService ioService;
    private final CommentDtoMapper commentDtoMapper;
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;


    @Override
    @Transactional(readOnly = true)
    public long getCommentCount() {
        return commentRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto getCommentByID(long id) {
        try {
            CommentDto commentDto = commentDtoMapper.convertCommentToDto(commentRepository.getById(id));
            return commentDto;
        } catch (Exception e) {
            throw new CommentNotFoundException("По данному id комментарий не найден");
        }
    }

    @Override
    @Transactional
    public void insertComment() {
        ioService.outputText("Введите текст комментария");
        String text = ioService.inputText();
        ioService.outputText("Введите id книги, к которой относится комментарий");
        long id = Long.parseLong(ioService.inputText());
        Book book = bookRepository.getById(id);
        commentRepository.save(new Comment(book, text));
    }

    @Override
    @Transactional
    public void deleteCommentById() {
        ioService.outputText("Введите id комментария для удаления");
        long id = Long.parseLong(ioService.inputText());
        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            throw new CommentNotFoundException("Комментария с таким id не найдено");
        }
    }

    @Override
    @Transactional
    public void updateCommentTextById() {
        ioService.outputText("Введите id комментария для замены");
        long id = Long.parseLong(ioService.inputText());
        ioService.outputText("Введите новый текст комментария");
        String text = ioService.inputText();
        commentRepository.updateCommentTextById(id,text);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsByBookId() {
        ioService.outputText("Введите id книги");
        long id = Long.parseLong(ioService.inputText());
        List<CommentDto> commentDtoList = commentDtoMapper.convertListCommentsToDto(commentRepository.findByBook_Id(id));
        ioService.outputText(commentDtoList.toString());
        return commentDtoList;
    }
}
