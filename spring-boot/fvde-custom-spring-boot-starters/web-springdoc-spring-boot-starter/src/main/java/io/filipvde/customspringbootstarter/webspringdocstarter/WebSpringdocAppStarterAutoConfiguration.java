package io.filipvde.customspringbootstarter.webspringdocstarter;

import io.filipvde.customspringbootstarter.webspringdocstarter.i18n.I18nConfiguration;
import io.filipvde.customspringbootstarter.webspringdocstarter.swagger.OpenApiAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Locale;

@AutoConfiguration
//@Configuration
@Import({
	OpenApiAutoConfiguration.class,
	I18nConfiguration.class
})
//@EnableScheduling
@ComponentScan("io.filipvde.customspringbootstarter.webspringdocstarter")
@ConfigurationPropertiesScan("io.filipvde.customspringbootstarter.webspringdocstarter")
public class WebSpringdocAppStarterAutoConfiguration {

	/**
	 * Let's not depend on what the JVM has to say about it, fix it to 'en'
	 */
	public WebSpringdocAppStarterAutoConfiguration() {
		Locale.setDefault(Locale.ENGLISH);
	}
}
