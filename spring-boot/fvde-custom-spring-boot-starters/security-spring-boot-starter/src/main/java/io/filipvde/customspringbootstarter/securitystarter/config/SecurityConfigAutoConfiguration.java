package io.filipvde.customspringbootstarter.securitystarter.config;

import io.filipvde.customspringbootstarter.securitystarter.security.jwt.JwtAuthenticationFilter;
import io.filipvde.customspringbootstarter.securitystarter.security.jwt.JwtProperties;
import io.filipvde.customspringbootstarter.securitystarter.security.jwt.JwtTokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * crossDomainSettings，supportDifferentRequestPaths，Configure different cross-domain information configurations
 *
 * <p>
 * Example:
 * <pre class="code">
 *   {@code
 *      hsweb:
 *        cors:
 *          enable: true
 *          configs:
 *            - /**:
 *                allowed-headers: "*"
 *                allowed-methods: ["GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"]
 *                allowed-origins: ["http://xxx.example.com"]
 *                allow-credentials: true
 *                maxAge: 1800
 *   }
 * </pre>
 * <p>
 * enable设为true，但是configs未配置，将使用已下的默认配置:
 * <pre class="code">
 *   {@code
 *     microservice:
 *   		security:
 *     			config:
 *       			allowed-urls-without-permission:
 *         				- /h2
 *         				- /h2-console
 *   }
 * </pre>
 */
//@AutoConfiguration
@Configuration
//@ConditionalOnProperty(prefix = "microservice.security.config", name = "enable", havingValue = "true")
@EnableConfigurationProperties(value = {
	SecurityConfigProperties.class,
	JwtProperties.class
})
@PropertySource(value = {"classpath:securityconfig-default.properties",})
public class SecurityConfigAutoConfiguration {

	private static final Logger log = LoggerFactory.getLogger(SecurityConfigAutoConfiguration.class);

	private SecurityConfigProperties securityConfigProperties;
	private JwtProperties jwtProperties;

	public SecurityConfigAutoConfiguration(SecurityConfigProperties securityConfigProperties,
										   JwtProperties jwtProperties) {
		this.securityConfigProperties = securityConfigProperties;
		log.info("SecurityConfigAutoConfiguration");
		log.info(securityConfigProperties.getAllowedUrlsWithoutPermission().toString());
		System.out.println(securityConfigProperties.getAllowedUrlsWithoutPermission().toString());

		this.jwtProperties = jwtProperties;
	}

	@Bean
	@ConditionalOnMissingBean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Bean
	@ConditionalOnMissingBean
	public JwtTokenManager jwtTokenManager() {
		return new JwtTokenManager(jwtProperties);
	}

	@EventListener
	public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
		log.info("SecurityConfigAutoConfiguration: onApplicationEvent: event type: ServletWebServerInitializedEvent");
		log.info(securityConfigProperties.getAllowedUrlsWithoutPermission().toString());
		System.out.println(securityConfigProperties.getAllowedUrlsWithoutPermission().toString());
	}

	@EventListener
	public void onApplicationEvent2(final ApplicationReadyEvent event) {
		log.info("SecurityConfigAutoConfiguration: onApplicationEvent2: event type: ApplicationReadyEvent");
		log.info(securityConfigProperties.getAllowedUrlsWithoutPermission().toString());
		System.out.println(securityConfigProperties.getAllowedUrlsWithoutPermission().toString());
	}

	@Bean
	@ConditionalOnMissingBean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
