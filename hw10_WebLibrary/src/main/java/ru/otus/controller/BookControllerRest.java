package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dto.BookDto;
import ru.otus.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookControllerRest {
    private final BookService bookService;

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks(){
        List<BookDto> bookDtoList = bookService.getAllBooks();
        System.out.println(bookDtoList.get(0).getTitle());
        return bookService.getAllBooks();
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable long id){
        System.out.println(id);
        bookService.deleteBookById(id);
    }
}
