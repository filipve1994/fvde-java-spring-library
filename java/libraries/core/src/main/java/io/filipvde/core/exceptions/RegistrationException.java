package io.filipvde.core.exceptions;

public class RegistrationException extends RuntimeException {

    private String errorMessage;

    public RegistrationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public RegistrationException(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public RegistrationException(String message, Throwable cause, String errorMessage) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }

    public RegistrationException(Throwable cause, String errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    public RegistrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorMessage) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}