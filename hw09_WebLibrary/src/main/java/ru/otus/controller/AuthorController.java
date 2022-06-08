package ru.otus.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.dto.AuthorDto;
import ru.otus.service.AuthorService;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/authorList")
    public String listAuthor(Model model) {
        List<AuthorDto> authorDtoList = authorService.getAllAuthors();
        model.addAttribute("authors", authorDtoList);
        return "authorList";
    }

    @GetMapping("/authorDelete")
    public String authorDelete(@RequestParam("id") long id) {
        authorService.deleteAuthorById(id);
        return "redirect:/authorList";
    }
}
