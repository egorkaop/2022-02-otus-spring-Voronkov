package ru.otus.exceptions;

public class GenreNotFoundException extends LibraryException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
