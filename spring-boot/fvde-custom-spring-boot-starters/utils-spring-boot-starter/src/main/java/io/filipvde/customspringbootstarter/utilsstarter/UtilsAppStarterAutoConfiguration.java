package io.filipvde.customspringbootstarter.utilsstarter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Locale;

@AutoConfiguration
@Import({

})
//@EnableScheduling
@ComponentScan("io.filipvde.customspringbootstarter.utilsstarter")
public class UtilsAppStarterAutoConfiguration {

    /**
     * Let's not depend on what the JVM has to say about it, fix it to 'en'
     */
    public UtilsAppStarterAutoConfiguration() {
        Locale.setDefault(Locale.ENGLISH);
    }
}
