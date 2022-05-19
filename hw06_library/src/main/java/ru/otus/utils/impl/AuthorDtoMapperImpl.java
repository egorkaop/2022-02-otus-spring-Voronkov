package ru.otus.utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.domain.Author;
import ru.otus.dto.AuthorDto;
import ru.otus.utils.AuthorDtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorDtoMapperImpl implements AuthorDtoMapper {

    @Override
    public AuthorDto convertAuthorToDto(Author author) {
        String name = author.getName();
        String surname = author.getSurname();
        return new AuthorDto(name, surname);
    }

    @Override
    public List<AuthorDto> convertListAuthorsToDto(List<Author> authorList) {
        return authorList.stream()
                .map(this::convertAuthorToDto)
                .collect(Collectors.toList());
    }
}
