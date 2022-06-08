package ru.otus.exceptions;

public class CommentNotFoundException extends LibraryException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
