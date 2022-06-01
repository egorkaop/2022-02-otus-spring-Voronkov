package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.dto.BookDto;
import ru.otus.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("/bookList")
    public String listBook(Model model){
        List<BookDto> bookDto = bookService.getAllBooks();
        model.addAttribute("books",bookDto);
        return "bookList";
    }

    @GetMapping("/bookEdit")
    public String editPage(@RequestParam("id") long id,Model model){
        BookDto bookDto = bookService.getBookByID(id);
        model.addAttribute("book",bookDto);
        return "bookEdit";
    }

    @PostMapping("/bookEdit")
    public String saveBook(@ModelAttribute BookDto book) {
        bookService.updateBookTitleById(book.getId(), book.getTitle());
        return "redirect:/bookList";
    }

    @GetMapping("/bookDelete")
    public String deleteBook(@RequestParam("id") long id){
        bookService.deleteBookById(id);
        return "redirect:/bookList";
    }
    @GetMapping("/bookFull")
    public String getFullBook(@RequestParam("id") long id,Model model){
        BookDto bookDto = bookService.getBookByID(id);
        model.addAttribute("book",bookDto);
        return "/bookFull";
    }

    @GetMapping("/bookInsert")
    public String insertPage(){
        return "/bookInsert";
    }
}
