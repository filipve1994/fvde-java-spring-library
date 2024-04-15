package io.filipvde.customspringbootstarter.securitystarter.security.jwt;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@ConditionalOnProperty(prefix = "microservice.security.jwt", name = "enable", havingValue = "false")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class JwtSecurityConfig {

	private static final Logger log = LoggerFactory.getLogger(JwtSecurityConfig.class);

}
