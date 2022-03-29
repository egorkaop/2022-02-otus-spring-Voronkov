package ru.otus.exceptions;

public abstract class BaseTestException extends RuntimeException {

    public BaseTestException(String message) {
        super(message);
    }
}
