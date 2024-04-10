package io.filipvde.customspringbootstarter.model.global;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Getter;
import org.fve.customstarters.model.exceptions.PreConditionNotMetException;

@Getter
@Schema(enumAsRef = true)
public enum Language {
    EN("EN"),
    FR("FR"),
    NL("NL");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public static Language getEnum(@Nullable final String value) {
        for (final Language enumCode : Language.values()) {
            if (enumCode.getValue().equalsIgnoreCase(value)) {
                return enumCode;

            }
        }

        throw new PreConditionNotMetException("No Language found for argument: " + value);
    }
}
