package io.filipvde.customspringbootstarter.security.jwt;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@ConditionalOnExpression("${security.jwt.activated:false}")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
@AllArgsConstructor
public class JwtSecurityConfig {
}
