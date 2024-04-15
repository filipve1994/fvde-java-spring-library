package io.filipvde.customspringbootstarter.securitystarter;

import io.filipvde.customspringbootstarter.securitystarter.config.SecurityConfigAutoConfiguration;
import io.filipvde.customspringbootstarter.securitystarter.security.jwt.JwtAuthenticationFilter;
import io.filipvde.customspringbootstarter.securitystarter.security.jwt.JwtProperties;
import io.filipvde.customspringbootstarter.securitystarter.security.jwt.JwtSecurityConfig;
import io.filipvde.customspringbootstarter.securitystarter.security.jwt.JwtTokenManager;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Locale;

@AutoConfiguration
@Import({
	SecurityConfigAutoConfiguration.class,
	JwtSecurityConfig.class,
	JwtTokenManager.class,
	JwtAuthenticationFilter.class,

})
//@EnableScheduling
@ComponentScan("io.filipvde.customspringbootstarter.securitystarter")
public class SecurityAppStarterAutoConfiguration {

    /**
     * Let's not depend on what the JVM has to say about it, fix it to 'en'
     */
    public SecurityAppStarterAutoConfiguration() {
        Locale.setDefault(Locale.ENGLISH);
    }

	@Bean
	@ConditionalOnMissingBean
	public JwtProperties jwtProperties() {
		return new JwtProperties();
	}

}
