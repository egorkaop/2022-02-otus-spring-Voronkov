package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookDao;
import ru.otus.dao.CommentDao;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.dto.CommentDto;
import ru.otus.exceptions.CommentNotFoundException;
import ru.otus.service.IOService;
import ru.otus.service.ShellCommentService;
import ru.otus.utils.CommentDtoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellCommentServiceImpl implements ShellCommentService {
    private final CommentDao commentDao;
    private final BookDao bookDao;
    private final IOService ioService;
    private final CommentDtoMapper commentDtoMapper;


    @Override
    @Transactional(readOnly = true)
    public void getCommentCount() {
        ioService.outputText("Количество комментариев: " + commentDao.count());
    }

    @Override
    @Transactional(readOnly = true)
    public void getCommentByID() {
        ioService.outputText("Введите id комментария");
        long id = Long.parseLong(ioService.inputText());
        try {
            CommentDto commentDto = commentDtoMapper.convertCommentToDto(commentDao.getCommentById(id));
            ioService.outputText(commentDto.toString());
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
        Book book = bookDao.getBookById(id);
        commentDao.insertComment(new Comment(book, text));
    }

    @Override
    @Transactional
    public void deleteCommentById() {
        ioService.outputText("Введите id комментария для удаления");
        long id = Long.parseLong(ioService.inputText());
        try {
            commentDao.deleteCommentById(id);
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
        commentDao.updateCommentTextById(id, text);
    }

    @Override
    @Transactional(readOnly = true)
    public void getAllCommentsByBookId() {
        ioService.outputText("Введите id книги");
        long id = Long.parseLong(ioService.inputText());
        Book book = bookDao.getBookById(id);
        List<CommentDto> commentDtoList = commentDtoMapper.convertListCommentsToDto(commentDao.getCommentsByBookId(book));
        ioService.outputText(commentDtoList.toString());
    }
}
