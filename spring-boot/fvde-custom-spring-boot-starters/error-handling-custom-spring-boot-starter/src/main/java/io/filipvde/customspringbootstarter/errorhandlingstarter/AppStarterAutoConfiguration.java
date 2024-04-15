package io.filipvde.customspringbootstarter.errorhandlingstarter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Locale;

@AutoConfiguration
@Import({

})
//@EnableScheduling
@ComponentScan("io.filipvde.customspringbootstarter.errorhandlingstarter")
@ConfigurationPropertiesScan("io.filipvde.customspringbootstarter.errorhandlingstarter")
public class AppStarterAutoConfiguration {

	/**
	 * Let's not depend on what the JVM has to say about it, fix it to 'en'
	 */
	public AppStarterAutoConfiguration() {
		Locale.setDefault(Locale.ENGLISH);
	}
}
