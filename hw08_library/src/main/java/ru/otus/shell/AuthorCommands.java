package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.IOService;
import ru.otus.service.ShellAuthorService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {
    private final ShellAuthorService shellAuthorService;
    private final IOService ioService;

    @ShellMethod(value = "authorInsert", key = {"ai", "authorIns", "authorInsert"})
    public void authorInsert() {
        shellAuthorService.insertAuthor();
        ioService.outputText("Автор добавлен");
    }

    @ShellMethod(value = "authorCount", key = {"ac", "authorCount"})
    public void authorCount() {
        shellAuthorService.getAuthorCount();
    }

    @ShellMethod(value = "authorGetByName", key = {"ag", "authorGetById"})
    public void authorGetByName() {
        shellAuthorService.getAuthorByName();
    }

    @ShellMethod(value = "authorGetAll", key = {"agall", "authorGetAll"})
    public void authorGetAll() {
        shellAuthorService.getAllAuthors();
    }

    @ShellMethod(value = "authorDeleteByName", key = {"ad", "authorDeleteById"})
    public void authorDeleteByName() {
        shellAuthorService.deleteAuthorByName();
        ioService.outputText("Автор удалён");
    }

    @ShellMethod(value = "authorNameUpdateByName", key = {"au", "authorNameUpdateById"})
    public void authorNameUpdateByName() {
        shellAuthorService.updateAuthorNameByName();
    }

}
