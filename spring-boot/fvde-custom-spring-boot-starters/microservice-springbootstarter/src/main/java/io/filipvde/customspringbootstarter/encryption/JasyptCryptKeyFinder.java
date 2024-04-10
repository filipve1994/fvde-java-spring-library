package io.filipvde.customspringbootstarter.encryption;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.fve.customstarters.model.exceptions.ConfigFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class JasyptCryptKeyFinder {

    @Autowired
    private JasyptPasswordProperties config;

    @Nonnull
    @SuppressWarnings({"squid:S134"}) // nested ifs
    protected String getJasyptPassword() {
        Path jasyptFIlePath = Paths.get(config.getFile());
        try {
            if (jasyptFIlePath.toFile().exists()) {
                try (Stream<String> jasyptFileLines = Files.lines(jasyptFIlePath)) {
                    final List<String> lines = jasyptFileLines.filter(StringUtils::isNotEmpty).collect(Collectors.toList());
                    if (lines.isEmpty()) {
                        throw new ConfigFailedException("jasypt password file is empty.");
                    }
                    if (config.getIndex() > lines.size()) {
                        throw new ConfigFailedException("jasypt password file contains less lines than indicated by index.");
                    }
                    //return the last password found
                    return lines.get(config.getIndex() - 1);
                }
            }
        } catch (IOException e) {
            throw new ConfigFailedException("Could not read the found jasypt file " + jasyptFIlePath.toAbsolutePath() + ", error: " + e, e);
        }
        log.warn("Jasypt password file not found in location: " + jasyptFIlePath.toAbsolutePath() + ", using JVM property instead.");
        return getRequiredProperty("jasypt.encryptor.password");

    }

    @Nonnull
    private static String getRequiredProperty(@Nonnull final String key) {
        return Optional.ofNullable(System.getProperty(key))
                .orElseThrow(() -> new IllegalArgumentException("Required property missing: " + key));
    }
}
