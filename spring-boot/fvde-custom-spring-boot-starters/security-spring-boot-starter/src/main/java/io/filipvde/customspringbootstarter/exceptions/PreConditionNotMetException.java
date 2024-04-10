package io.filipvde.customspringbootstarter.exceptions;

public class PreConditionNotMetException extends RuntimeException {

	public PreConditionNotMetException() {
		super();
	}

	public PreConditionNotMetException(String message) {
		super(message);
	}

	public PreConditionNotMetException(String message, Throwable cause) {
		super(message, cause);
	}

	public PreConditionNotMetException(Throwable cause) {
		super(cause);
	}

	protected PreConditionNotMetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
