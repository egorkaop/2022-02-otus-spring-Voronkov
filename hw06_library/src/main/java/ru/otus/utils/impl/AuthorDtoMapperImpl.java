package ru.otus.utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Author;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.utils.AuthorDtoMapper;
import ru.otus.utils.BookDtoMapper;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorDtoMapperImpl implements AuthorDtoMapper {
    private final BookDtoMapper bookDtoMapper;

    @Override
    public AuthorDto convertAuthorToDto(Author author) {
        String name = author.getName();
        String surname = author.getSurname();
        List<BookDto> bookDtoList = bookDtoMapper.convertListBooksToDto(author.getBooks());
        return new AuthorDto(name, surname, bookDtoList);
    }

    @Override
    public List<AuthorDto> convertListAuthorsToDto(List<Author> authorList) {
        List<AuthorDto> authorDtoList = new ArrayList<>();
        for (Author author : authorList) {
            authorDtoList.add(convertAuthorToDto(author));
        }
        return authorDtoList;
    }
}
