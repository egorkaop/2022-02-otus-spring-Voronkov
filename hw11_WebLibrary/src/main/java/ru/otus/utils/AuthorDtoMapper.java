package ru.otus.utils;

import ru.otus.domain.Author;
import ru.otus.dto.AuthorDto;

import java.util.List;

public interface AuthorDtoMapper {
    AuthorDto convertAuthorToDto(Author author);

    List<AuthorDto> convertListAuthorsToDto(List<Author> authorList);

}
