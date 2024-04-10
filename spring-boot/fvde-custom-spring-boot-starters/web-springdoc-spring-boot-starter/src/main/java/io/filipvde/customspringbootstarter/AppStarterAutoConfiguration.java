package io.filipvde.customspringbootstarter;

import io.filipvde.customspringbootstarter.swagger.ApiInfoProperties;
import io.filipvde.customspringbootstarter.swagger.OpenApiAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Locale;

@AutoConfiguration
//@Configuration
@Import({
	OpenApiAutoConfiguration.class
})
//@EnableScheduling
//@ComponentScan("io.filipvde.customspringbootstarter.config")
public class AppStarterAutoConfiguration {

    /**
     * Let's not depend on what the JVM has to say about it, fix it to 'en'
     */
    public AppStarterAutoConfiguration() {
        Locale.setDefault(Locale.ENGLISH);
    }
}
