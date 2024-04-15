package io.filipvde.customspringbootstarter.microservicestarter;

import io.filipvde.customspringbootstarter.microservicestarter.global.GlobalAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Locale;

@AutoConfiguration
@Import({
        GlobalAutoConfiguration.class,
})
//@EnableScheduling
@ComponentScan("io.filipvde.customspringbootstarter.microservicestarter")
public class MicroserviceStarterAutoConfiguration {

    /**
     * Let's not depend on what the JVM has to say about it, fix it to 'en'
     */
    public MicroserviceStarterAutoConfiguration() {
        Locale.setDefault(Locale.ENGLISH);
    }
}
