package io.filipvde.domain.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(enumAsRef = true)
@Getter
public enum Status {
    SUCCESS("Success"),
    FAILED("Failed");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}