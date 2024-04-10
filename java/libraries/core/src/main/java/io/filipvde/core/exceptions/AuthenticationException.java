package io.filipvde.core.exceptions;

public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = -4778173207515812187L;

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable th) {
        super(message, th);
    }
}