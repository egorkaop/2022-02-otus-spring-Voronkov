package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dao.AuthorRepository;
import ru.otus.dao.BookRepository;
import ru.otus.dao.GenreRepository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.dto.BookDto;
import ru.otus.dto.BookInsertDto;
import ru.otus.dto.BookUpdateDto;
import ru.otus.utils.BookDtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookControllerRest {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookDtoMapper bookDtoMapper;

    @GetMapping("/api/books")
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @DeleteMapping("/api/books/{id}")
    public Mono<Void> deleteBook(@PathVariable String id) {
        return bookRepository.deleteById(id);
    }

    @PatchMapping("/api/books/{id}")
    public Mono<Object> updateBook(@PathVariable String id, BookUpdateDto bookUpdateDto) {
        return bookRepository.findById(id).map(b -> {
            b.setTitle(bookUpdateDto.getTitle());
            return bookRepository.save(b).subscribe();
        });
    }

    @GetMapping("/api/books/{id}")
    public Mono<BookDto> getFullBook(@PathVariable(name = "id") String id) {
        return bookRepository.findById(id).map(b->bookDtoMapper.convertBookToDto(b));
    }

//    @PostMapping("/api/books")
//    public Mono<Object> insertBook(BookInsertDto bookInsertDto) {
//        List<Author> authors = new ArrayList<>();
//        authorRepository.findAllById(bookInsertDto.getAuthors()).map(authors::add).subscribe();
//        System.out.println(authors.size());
//        return null;
//    }
}
