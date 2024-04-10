package io.filipvde.customspringbootstarter.exceptions;

import jakarta.annotation.Nullable;

public class PreConditionNotMetException extends BadRequestException {
    public PreConditionNotMetException(@Nullable final String message) {
        super(message);
    }
}
