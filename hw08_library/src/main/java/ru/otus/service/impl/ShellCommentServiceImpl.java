package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookRepository;
import ru.otus.dao.CommentRepository;
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
    private final IOService ioService;
    private final CommentDtoMapper commentDtoMapper;
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;


    @Override
    public void getCommentCount() {
        ioService.outputText("Количество комментариев: " + commentRepository.count());
    }

    @Override
    public void getCommentByText() {
        ioService.outputText("Введите текст комментария");
        String commentText = ioService.inputText();
        try {
            List<CommentDto> commentDto = commentDtoMapper.convertListCommentsToDto(commentRepository.findByText(commentText));
            ioService.outputText(commentDto.toString());
        } catch (Exception e) {
            throw new CommentNotFoundException("По данному тексту комментарий не найден");
        }
    }

    @Override
    public void insertComment() {
        ioService.outputText("Введите текст комментария");
        String commentText = ioService.inputText();
        ioService.outputText("Введите название книги, к которой относится комментарий");
        String bookTitle = ioService.inputText();
        Book book = bookRepository.findByTitle(bookTitle);
        commentRepository.save(new Comment(book, commentText));
    }

    @Override
    public void deleteCommentByText() {
        ioService.outputText("Введите текст комментария для удаления");
        String commentText = ioService.inputText();
        try {
            commentRepository.deleteByText(commentText);
        } catch (Exception e) {
            throw new CommentNotFoundException("Комментария с таким текстом не найдено");
        }
    }

    @Override
    public void updateCommentTextByText() {
        ioService.outputText("Введите текст комментария для замены");
        String oldText = ioService.inputText();
        ioService.outputText("Введите новый текст комментария");
        String newText = ioService.inputText();
        commentRepository.findByText(oldText).stream().peek(x -> x.setText(newText)).forEach(commentRepository::save);
    }

    @Override
    public void getAllCommentsByBookTitle() {
        ioService.outputText("Введите название книги");
        String bookTitle = ioService.inputText();
        Book book = bookRepository.findByTitle(bookTitle);
        List<CommentDto> commentDtoList = commentDtoMapper.convertListCommentsToDto(commentRepository.findByBook(book));
        ioService.outputText(commentDtoList.toString());
    }
}
