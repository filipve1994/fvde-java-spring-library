package io.filipvde.customspringbootstarter.securitystarter.security.model;

import io.filipvde.customspringbootstarter.securitystarter.exceptions.PreConditionNotMetException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Getter;

@Getter
@Schema(enumAsRef = true)
public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public static UserRole getEnum(@Nullable final String value) {
        for (final UserRole enumCode : UserRole.values()) {
            if (enumCode.getValue().equalsIgnoreCase(value)) {
                return enumCode;

            }
        }

        throw new PreConditionNotMetException("No UserRole found for argument: " + value);
    }
}
