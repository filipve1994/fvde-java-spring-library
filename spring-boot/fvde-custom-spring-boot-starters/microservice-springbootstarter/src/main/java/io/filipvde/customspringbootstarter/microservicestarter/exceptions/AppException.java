package io.filipvde.customspringbootstarter.microservicestarter.exceptions;

import org.springframework.lang.NonNull;

public class AppException extends RuntimeException {

    public AppException() {
        super();
    }

    public AppException(@NonNull String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    protected AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}