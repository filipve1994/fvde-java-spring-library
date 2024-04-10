package io.filipvde.domain.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(enumAsRef = true)
@Getter
public enum Role {
    ADMIN("ADMIN"),
    USER("USER"),
    GUEST("GUEST");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
