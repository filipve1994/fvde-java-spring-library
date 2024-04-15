package io.filipvde.customspringbootstarter.thymeleafstarter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Locale;

@AutoConfiguration
@Import({

})
//@EnableScheduling
@ComponentScan("io.filipvde.customspringbootstarter.thymeleafstarter")
public class ThymeleafAppStarterAutoConfiguration {

    /**
     * Let's not depend on what the JVM has to say about it, fix it to 'en'
     */
    public ThymeleafAppStarterAutoConfiguration() {
        Locale.setDefault(Locale.ENGLISH);
    }
}
