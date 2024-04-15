package io.filipvde.customspringbootstarter.webspringdocstarter.logging;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({
	RequestLoggingFilterConfig.class
})
public class LoggingAutoConfiguration {
}
