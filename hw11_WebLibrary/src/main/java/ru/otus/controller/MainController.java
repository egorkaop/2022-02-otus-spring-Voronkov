package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import ru.otus.dao.BookRepository;
import ru.otus.domain.Book;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final BookRepository bookRepository;

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

}
