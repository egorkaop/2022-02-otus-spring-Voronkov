package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.IOService;
import ru.otus.service.ShellCommentService;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {
    private final ShellCommentService shellCommentService;
    private final IOService ioService;

    @ShellMethod(value = "commentInsert", key = {"ci", "commentIns", "commentInsert"})
    public void commentInsert() {
        shellCommentService.insertComment();
        ioService.outputText("Комментариц добавлен");
    }

    @ShellMethod(value = "commentCount", key = {"cc", "commentCount"})
    public void commentCount() {
        shellCommentService.getCommentCount();
    }

    @ShellMethod(value = "commentGetById", key = {"cg", "commentGetById"})
    public void commentGetById() {
        shellCommentService.getCommentByID();
    }

    @ShellMethod(value = "commentGetAll", key = {"cgall", "commentGetAll"})
    public void commentGetAll() {
        shellCommentService.getAllComments();
    }

    @ShellMethod(value = "commentDeleteById", key = {"cd", "commentDeleteById"})
    public void commentDeleteById() {
        shellCommentService.deleteCommentById();
        ioService.outputText("Комментарий удалён удалён");
    }

    @ShellMethod(value = "commentTextUpdateById", key = {"cu", "commentTextUpdateById"})
    public void commentTextUpdateById() {
        shellCommentService.updateCommentTextById();
    }
}
