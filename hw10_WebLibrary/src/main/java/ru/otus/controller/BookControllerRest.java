package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.dto.BookDto;
import ru.otus.dto.BookInsertDto;
import ru.otus.dto.BookUpdateDto;
import ru.otus.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookControllerRest {
    private final BookService bookService;

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable long id) {
        System.out.println(id);
        bookService.deleteBookById(id);
    }

    @PatchMapping("/api/books/{id}")
    public void updateBook(@PathVariable long id, BookUpdateDto bookUpdateDto) {
        bookService.updateBookTitleById(id, bookUpdateDto);
    }

    @GetMapping("/api/books/{id}")
    public BookDto getFullBook(@PathVariable(name = "id") long id) {
        return bookService.getBookByID(id);
    }

    @PostMapping("/api/books")
    public void insertBook(BookInsertDto bookInsertDto) {
        bookService.insertBook(bookInsertDto);
    }
}
