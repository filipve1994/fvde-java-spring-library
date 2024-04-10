package io.filipvde.customspringbootstarter.encryption;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import(JasyptCryptKeyFinder.class)
@EnableConfigurationProperties(JasyptPasswordProperties.class)
public class JasyptAutoConfiguration {

    @Bean("jasyptStringEncryptor")
    @Nonnull
    public StringEncryptor stringEncryptor(@Nonnull final JasyptCryptKeyFinder cryptKeyFinder) {
        log.info("Warming up JasyptAutoConfiguration.StringEncryptor bean");
        final PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        final SimpleStringPBEConfig jasyptConfig = new SimpleStringPBEConfig();
        jasyptConfig.setPassword(cryptKeyFinder.getJasyptPassword());
        jasyptConfig.setAlgorithm("PBEWithMD5AndDES");
        jasyptConfig.setKeyObtentionIterations("1000");
        jasyptConfig.setPoolSize("1");
        jasyptConfig.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        jasyptConfig.setStringOutputType("base64");
        encryptor.setConfig(jasyptConfig);
        return encryptor;
    }
}
