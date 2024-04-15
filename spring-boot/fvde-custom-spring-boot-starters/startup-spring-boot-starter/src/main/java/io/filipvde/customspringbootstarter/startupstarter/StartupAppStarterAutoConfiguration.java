package io.filipvde.customspringbootstarter.startupstarter;

import io.filipvde.customspringbootstarter.startupstarter.startup.AppDetails;
import io.filipvde.customspringbootstarter.startupstarter.startup.LogServerDetailsOnStartup;
import io.filipvde.customspringbootstarter.startupstarter.startup.StartupProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Locale;

/**
 * TODO: rename this file to unique classname that represents your starter functionality
 * TODO: and rename the examplestarter package to functionality for example jpastarter
 */
@AutoConfiguration
@Import({

})
//@EnableScheduling
@ComponentScan("io.filipvde.customspringbootstarter.startupstarter")
public class StartupAppStarterAutoConfiguration {

	/**
	 * Let's not depend on what the JVM has to say about it, fix it to 'en'
	 */
	public StartupAppStarterAutoConfiguration() {
		Locale.setDefault(Locale.ENGLISH);
	}

	@Bean
	public AppDetails appDetails() {
		return new AppDetails();
	}

	@Bean
	public StartupProperties startupProperties() {
		return new StartupProperties();
	}

	@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
	@Bean
	public LogServerDetailsOnStartup logServerDetailsOnStartup() {
		return new LogServerDetailsOnStartup();
	}

}
