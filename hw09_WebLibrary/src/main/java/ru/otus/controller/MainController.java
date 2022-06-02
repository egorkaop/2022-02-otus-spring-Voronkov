package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.otus.service.BookService;



@Controller
@RequiredArgsConstructor
public class MainController {


    @GetMapping("/")
    public String mainPage(Model model){
        return "mainPage";
    }
}
