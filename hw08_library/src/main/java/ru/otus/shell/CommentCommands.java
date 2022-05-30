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

    @ShellMethod(value = "commentGetByText", key = {"cg", "commentGetById"})
    public void commentGetByText() {
        shellCommentService.getCommentByText();
    }

    @ShellMethod(value = "commentDeleteByText", key = {"cd", "commentDeleteById"})
    public void commentDeleteByText() {
        shellCommentService.deleteCommentByText();
        ioService.outputText("Комментарий удалён удалён");
    }

    @ShellMethod(value = "commentTextUpdateByText", key = {"cu", "commentTextUpdateById"})
    public void commentTextUpdateByText() {
        shellCommentService.updateCommentTextByText();
    }

    @ShellMethod(value = "commentTextByBookTitle", key = {"cgb", "commentTextByBookId"})
    public void commentTextByBookText() {
        shellCommentService.getAllCommentsByBookTitle();
    }
}
