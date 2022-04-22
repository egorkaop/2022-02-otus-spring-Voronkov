package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.IOService;
import ru.otus.service.ShellBookService;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final IOService ioService;
    private final ShellBookService shellBookService;


    @ShellMethod(value = "bookInsert", key = {"bi", "bookIns", "bookInsert"})
    public void bookInsert() {
        shellBookService.insertBook();
        ioService.outputText("Книга добавлена");
    }

    @ShellMethod(value = "bookCount", key = {"bc", "bookCount"})
    public void bookCount() {
        shellBookService.getBookCount();
    }

    @ShellMethod(value = "bookGetById", key = {"bg", "bookGetById"})
    public void bookGetById() {
        shellBookService.getBookByID();
    }

    @ShellMethod(value = "bookGetAll", key = {"bgall", "bookGetAll"})
    public void bookGetAll() {
        shellBookService.getAllBooks();
    }

    @ShellMethod(value = "bookDeleteById", key = {"bd", "bookDeleteById"})
    public void bookDeleteById() {
        shellBookService.deleteBookById();
        ioService.outputText("Книга удалена");
    }
}
