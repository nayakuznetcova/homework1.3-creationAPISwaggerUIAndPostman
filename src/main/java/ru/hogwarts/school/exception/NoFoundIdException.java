package ru.hogwarts.school.exception;

public class NoFoundIdException extends Exception{
    public NoFoundIdException() {
    }

    public NoFoundIdException(String message) {
        super(message);
    }

    public NoFoundIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFoundIdException(Throwable cause) {
        super(cause);
    }

    public NoFoundIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
