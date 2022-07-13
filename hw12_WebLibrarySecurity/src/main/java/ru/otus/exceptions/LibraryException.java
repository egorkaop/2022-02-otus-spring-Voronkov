package ru.otus.exceptions;

public abstract class LibraryException extends RuntimeException {
    public LibraryException(String message) {
        super(message);
    }
}
