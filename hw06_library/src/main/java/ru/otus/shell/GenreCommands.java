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

    @ShellMethod(value = "genreGetById", key = {"gg", "genreGetById"})
    public void genreGetById() {
        shellGenreService.getGenreByID();
    }

    @ShellMethod(value = "genreGetAll", key = {"ggall", "genreGetAll"})
    public void genreGetAll() {
        shellGenreService.getAllGenres();
    }

    @ShellMethod(value = "genreDeleteById", key = {"gd", "genreDeleteById"})
    public void genreDeleteById() {
        shellGenreService.deleteGenreById();
        ioService.outputText("Жанр удалён");
    }

    @ShellMethod(value = "genreNameUpdateById", key = {"gu", "genreNameUpdateById"})
    public void genreNameUpdateById() {
        shellGenreService.updateGenreNameById();
    }

}
