package io.filipvde.customspringbootstarter.webspringdocstarter.cors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

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
 *      hsweb:
 *        cors:
 *          enable: true
 *          configs:
 *            - /**:
 *                allowed-headers: "*"
 *                allowed-methods: ["GET", "POST", "HEAD"]
 *                allowed-origins: "*"
 *                allow-credentials: true
 *                maxAge: 1800
 *   }
 * </pre>
 *
 * <p>
 * <b>注意:</b>
 * The attribute name of the object in the configuration file is in SpringBoot 2.3 Starting from this version, special characters are no longer supported.，会将特殊字符过滤掉，
 * onlySupports{@code [A-Za-z0-9\-\_]}，pleaseSeeTheSpecificDetails{@code ConfigurationPropertyName}类的{@code adapt}方法
 */
@AutoConfiguration
@Configuration
@ConditionalOnProperty(prefix = "microservice.web.cors", name = "enable", havingValue = "true")
@EnableConfigurationProperties(CorsProperties.class)
@PropertySource(value = {"classpath:cors-default.properties",})
public class CorsAutoConfiguration {

	@Autowired
	private CorsProperties corsProperties;

	@ConditionalOnClass(name = "jakarta.servlet.Filter")
	@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
	@Configuration
	static class WebMvcCorsConfiguration {
		@Bean
		public org.springframework.web.filter.CorsFilter corsFilter(CorsProperties corsProperties) {
			UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();

//			CorsProperties.CorsConfiguration config = new CorsProperties.CorsConfiguration().applyPermitDefaultValues();
//			corsConfigurationSource.registerCorsConfiguration(config.getPath(), buildConfiguration(config));

			corsConfigurationSource.registerCorsConfiguration(corsProperties.getPath(), buildConfiguration(corsProperties.applyPermitDefaultValues()));

			return new org.springframework.web.filter.CorsFilter(corsConfigurationSource);
		}
	}

	@Configuration
	@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
	static class WebFluxCorsConfiguration {
		@Bean
		public CorsWebFilter webFluxCorsRegistration(CorsProperties corsProperties) {
			org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource corsConfigurationSource = new org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource();

//			CorsProperties config = new CorsProperties.applyPermitDefaultValues();

//			corsConfigurationSource.registerCorsConfiguration(config.getPath(), buildConfiguration(config));

			corsConfigurationSource.registerCorsConfiguration(corsProperties.getPath(), buildConfiguration(corsProperties.applyPermitDefaultValues()));

			return new CorsWebFilter(corsConfigurationSource);
		}
	}


	private static CorsConfiguration buildConfiguration(CorsProperties config) {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedHeaders(config.getAllowedHeaders());
		corsConfiguration.setAllowedMethods(config.getAllowedMethods());
		corsConfiguration.setAllowedOrigins(config.getAllowedOrigins());
		corsConfiguration.setAllowCredentials(config.getAllowCredentials());
		corsConfiguration.setExposedHeaders(config.getExposedHeaders());
		corsConfiguration.setMaxAge(config.getMaxAge());

		return corsConfiguration;
	}
}