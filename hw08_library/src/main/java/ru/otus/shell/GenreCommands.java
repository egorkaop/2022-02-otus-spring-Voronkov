package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.IOService;
import ru.otus.service.ShellGenreService;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {
    private final IOService ioService;
    private final ShellGenreService shellGenreService;


    @ShellMethod(value = "genreInsert", key = {"gi", "genreIns", "genreInsert"})
    public void genreInsert() {
        shellGenreService.insertGenre();
        ioService.outputText("Жанр добавлен");
    }

    @ShellMethod(value = "genreCount", key = {"gc", "genreCount"})
    public void genreCount() {
        shellGenreService.getGenreCount();
    }

    @ShellMethod(value = "genreGetByName", key = {"gg", "genreGetById"})
    public void genreGetByName() {
        shellGenreService.getGenreByName();
    }

    @ShellMethod(value = "genreGetAll", key = {"ggall", "genreGetAll"})
    public void genreGetAll() {
        shellGenreService.getAllGenres();
    }

    @ShellMethod(value = "genreDeleteByName", key = {"gd", "genreDeleteById"})
    public void genreDeleteByName() {
        shellGenreService.deleteGenreByName();
        ioService.outputText("Жанр удалён");
    }

    @ShellMethod(value = "genreNameUpdateByName", key = {"gu", "genreNameUpdateById"})
    public void genreNameUpdateByName() {
        shellGenreService.updateGenreNameByName();
    }
}
