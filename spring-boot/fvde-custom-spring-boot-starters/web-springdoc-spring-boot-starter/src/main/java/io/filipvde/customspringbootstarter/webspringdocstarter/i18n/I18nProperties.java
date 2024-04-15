package io.filipvde.customspringbootstarter.webspringdocstarter.i18n;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("microservice.web.i18n")
public class I18nProperties {

	private String defaultLocale;

	private String defaultTimezone;

}
