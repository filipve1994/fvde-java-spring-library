package io.filipvde.customspringbootstarter.webspringdocstarter.i18n;

import io.filipvde.customspringbootstarter.webspringdocstarter.swagger.ApiInfoProperties;
import io.filipvde.customspringbootstarter.webspringdocstarter.swagger.OpenApiProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@ConditionalOnProperty(name = "springdoc.swagger-ui.enabled", matchIfMissing = true)
//@ConditionalOnProperty("microservice.swagger.controller-package")
@Configuration
@EnableConfigurationProperties({I18nProperties.class})
@PropertySource(value = {"classpath:i18n-default.properties",})
public class I18nConfiguration {
}
