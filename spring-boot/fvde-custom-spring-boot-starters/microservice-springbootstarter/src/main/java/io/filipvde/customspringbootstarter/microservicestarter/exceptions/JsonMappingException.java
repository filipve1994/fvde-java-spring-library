package io.filipvde.customspringbootstarter.microservicestarter.exceptions;

public class JsonMappingException extends RuntimeException {
    public JsonMappingException() {
        super();
    }

    public JsonMappingException(String message) {
        super(message);
    }

    public JsonMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonMappingException(Throwable cause) {
        super(cause);
    }

    protected JsonMappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
