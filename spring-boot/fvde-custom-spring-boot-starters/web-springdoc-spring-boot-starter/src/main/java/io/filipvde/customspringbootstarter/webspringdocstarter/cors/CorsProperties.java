package io.filipvde.customspringbootstarter.webspringdocstarter.cors;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "microservice.web.cors"/*, ignoreInvalidFields = true*/)
@Getter
@Setter
@ToString
public class CorsProperties {
	/**
	 * Wildcard representing <em>all</em> origins, methods, or headers.
	 */
	public static final String ALL = "*";

	private String path = "/**";

	private List<String> allowedOrigins;
//	private List<String> allowedOrigins = Lists.newArrayList("*");

	private List<String> allowedMethods;
//	private List<String> allowedMethods = Lists.newArrayList("*");

	private List<String> allowedHeaders;
//	private List<String> allowedHeaders = Lists.newArrayList("*");

	private List<String> exposedHeaders;

	private Boolean allowCredentials;

	private Long maxAge = 1800L;

	public void apply(CorsRegistration registry) {
		if (CollectionUtils.isNotEmpty(this.allowedHeaders)) {
			registry.allowedHeaders(this.getAllowedHeaders().toArray(new String[0]));
		}
		if (CollectionUtils.isNotEmpty(this.allowedMethods)) {
			registry.allowedMethods(this.getAllowedMethods().toArray(new String[0]));
		}
		if (CollectionUtils.isNotEmpty(this.allowedOrigins)) {
			registry.allowedOrigins(this.getAllowedOrigins().toArray(new String[0]));
		}
		if (CollectionUtils.isNotEmpty(this.exposedHeaders)) {
			registry.exposedHeaders(this.getExposedHeaders().toArray(new String[0]));
		}
		if (this.maxAge == null) {
			registry.maxAge(this.getMaxAge());
		}
		registry.allowCredentials(this.getAllowCredentials() == null || Boolean.TRUE.equals(this.getAllowCredentials()));
	}

	CorsProperties applyPermitDefaultValues() {
		if (this.allowedOrigins == null) {
			this.addAllowedOrigin();
		}
		if (this.allowedMethods == null) {
			this.setAllowedMethods(Arrays.asList(
				HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name()));
		}
		if (this.allowedHeaders == null) {
			this.addAllowedHeader();
		}
		if (this.allowCredentials == null) {
			this.setAllowCredentials(true);
		}
		if (this.maxAge == null) {
			this.setMaxAge(1800L);
		}
		return this;
	}

	/**
	 * Add an origin to allow.
	 */
	void addAllowedOrigin() {
		if (this.allowedOrigins == null) {
			this.allowedOrigins = new ArrayList<>(4);
		}
		this.allowedOrigins.add(CorsProperties.ALL);
	}

	/**
	 * Add an actual request header to allow.
	 */
	void addAllowedHeader() {
		if (this.allowedHeaders == null) {
			this.allowedHeaders = new ArrayList<>(4);
		}
		this.allowedHeaders.add(CorsProperties.ALL);
	}
}

