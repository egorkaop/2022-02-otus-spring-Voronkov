package ru.otus.exceptions;

public class AuthorNotFoundException extends LibraryException{
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
