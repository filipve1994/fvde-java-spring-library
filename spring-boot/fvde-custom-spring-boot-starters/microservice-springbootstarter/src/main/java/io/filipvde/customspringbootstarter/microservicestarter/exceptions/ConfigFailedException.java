package io.filipvde.customspringbootstarter.microservicestarter.exceptions;

public class ConfigFailedException extends AppException {
    public ConfigFailedException() {
        super();
    }

    public ConfigFailedException(String message) {
        super(message);
    }

    public ConfigFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigFailedException(Throwable cause) {
        super(cause);
    }

    protected ConfigFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
