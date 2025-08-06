package org.example.Hellena.CustomExceptions;

public class KeyCodeException extends RuntimeException {
    public KeyCodeException(String message) {
        super(message);
    }

    public KeyCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
