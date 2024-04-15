package io.filipvde.customspringbootstarter.securitystarter.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "microservice.security.jwt")
public class JwtProperties {

	//    private String issuer;
	private String issuer = "https://github.com/fve";

	//    private String secretKey;
	private String secretKey = "90c8c2c617816e6a2afe2c4ed8abcfeed5144a0d73a39648df3228690b64f45e";

	//    private long expirationMinute;
	private long expirationMinute = 10;

	//	private long expirationMilliseconds;
	private long expirationMilliseconds = 604800000;

}