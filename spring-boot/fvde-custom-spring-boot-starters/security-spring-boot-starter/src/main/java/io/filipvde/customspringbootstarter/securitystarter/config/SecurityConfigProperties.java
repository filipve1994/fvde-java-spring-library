package io.filipvde.customspringbootstarter.securitystarter.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "microservice.security.config")
@Getter
@Setter
@ToString
public class SecurityConfigProperties {

	//	private List<String> allowedUrlsWithoutPermission;
	private List<String> allowedUrlsWithoutPermission = Arrays.asList(
		"/h2", "/h2-console"
	);
}
