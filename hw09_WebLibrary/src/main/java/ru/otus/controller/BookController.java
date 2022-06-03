package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.dto.GenreDto;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
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
    @Validated
    @PostMapping("/bookEdit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto book,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "bookEdit";
        }
        bookService.updateBookTitleById(book.getId(), book.getTitle());
        return "redirect:/bookList";
    }

    @GetMapping("/bookDelete")
    public String deleteBook(@RequestParam("id") long id, HttpServletRequest request){
        String refer = request.getHeader("Referer");
        bookService.deleteBookById(id);
        return "redirect:" + refer;
    }
    @GetMapping("/bookFull")
    public String getFullBook(@RequestParam("id") long id,Model model){
        BookDto bookDto = bookService.getBookByID(id);
        model.addAttribute("book",bookDto);
        return "/bookFull";
    }

    @GetMapping("/bookInsert")
    public String insertPage(Model model){
        List<AuthorDto> authorDtoList = authorService.getAllAuthors();
        List<GenreDto> genreDtoList = genreService.getAllGenres();
        model.addAttribute("authors",authorDtoList);
        model.addAttribute("genres",genreDtoList);
        return "/bookInsert";
    }
    @PostMapping("/bookInsert")
    public String insertBook(String title, long[] authors, long[] genres){
        bookService.insertBook(title,authors,genres);
        return "redirect:/bookList";
    }

    @GetMapping("/booksByAuthor")
    public String getBooksByAuthor(@RequestParam("id") long id,Model model){
        List<BookDto> bookDtoList = bookService.getBooksByAuthorId(id);
        model.addAttribute("books",bookDtoList);
        return "bookList";
    }
    @GetMapping("/booksByGenre")
    public String getBooksByGenre(@RequestParam("id") long id, Model model){
        List<BookDto> bookDtoList = bookService.getBooksByGenreId(id);
        model.addAttribute("books",bookDtoList);
        return "bookList";
    }

}
