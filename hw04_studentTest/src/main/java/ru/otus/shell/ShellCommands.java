package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.IOService;
import ru.otus.service.QuestionService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final QuestionService questionService;
    private final IOService ioService;
    private static final String LOGIN_FORMAT="Добро пожаловать: %s \n";
    private String userName;

    @ShellMethod(value = "login", key = {"l","login"})
    public void login(@ShellOption(defaultValue = "userName") String userName){
        this.userName=userName;
        ioService.outputFormatText(LOGIN_FORMAT,userName);
    }

    @ShellMethod(value = "testing", key = {"t","test","testing"})
    @ShellMethodAvailability(value = "isStartTestCommandAvailable")
    public void startTest(){
        questionService.startTesting();
    }

    private Availability isStartTestCommandAvailable() {
        return userName == null? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }
}
