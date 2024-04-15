package io.filipvde.customspringbootstarter.examplestarter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Locale;

/**
 * TODO: rename this file to unique classname that represents your starter functionality
 * TODO: and rename the examplestarter package to functionality for example jpastarter
 *
 */
@AutoConfiguration
@Import({

})
//@EnableScheduling
@ComponentScan("io.filipvde.customspringbootstarter.examplestarter")
public class ExampleAppStarterAutoConfiguration {

    /**
     * Let's not depend on what the JVM has to say about it, fix it to 'en'
     */
    public ExampleAppStarterAutoConfiguration() {
        Locale.setDefault(Locale.ENGLISH);
    }
}
