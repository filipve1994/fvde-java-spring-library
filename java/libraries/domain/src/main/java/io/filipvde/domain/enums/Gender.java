package io.filipvde.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(enumAsRef = true)
@Getter
public enum Gender {
    UNKNOWN(0, "U"),
    FEMALE(1, "F"),
    MALE(2, "M");

    @JsonValue
    private final Integer code;

    @JsonValue
    private final String shortcode;

    Gender(Integer code, String shortcode) {
        this.code = code;
        this.shortcode = shortcode;
    }

}

