package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.dto.BookDto;
import ru.otus.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookControllerRest {
    private final BookService bookService;

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable long id){
        System.out.println(id);
        bookService.deleteBookById(id);
    }

    @PutMapping("/api/books/{id}/{title}")
    public void updateBook(@PathVariable long id,@PathVariable String title){
        System.out.println(id + " " + title);
        bookService.updateBookTitleById(id,title);
    }

    @GetMapping("/api/books/{id}")
    public BookDto getFullBook(@PathVariable long id){
        return bookService.getBookByID(id);
    }
}
