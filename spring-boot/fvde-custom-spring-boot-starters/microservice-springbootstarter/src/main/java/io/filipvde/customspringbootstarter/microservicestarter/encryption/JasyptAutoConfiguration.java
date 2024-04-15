package io.filipvde.customspringbootstarter.microservicestarter.encryption;

import jakarta.annotation.Nonnull;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JasyptCryptKeyFinder.class)
@EnableConfigurationProperties(JasyptPasswordProperties.class)
public class JasyptAutoConfiguration {

	private static final Logger log = LoggerFactory.getLogger(JasyptAutoConfiguration.class);


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
