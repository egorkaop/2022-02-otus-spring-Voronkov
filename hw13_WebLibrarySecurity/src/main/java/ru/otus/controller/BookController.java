package ru.otus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.GenreDto;

import java.util.List;

@Controller
public class BookController {
    @GetMapping("/insertBook")
    public String insertPage(){
        System.out.println("bookinsert");
        return "/insertBook";
    }
}
