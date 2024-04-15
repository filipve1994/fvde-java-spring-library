package io.filipvde.customspringbootstarter.startupstarter.startup;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("microservice.startup")
public class StartupProperties {

//	private String logMessageSuccess;
	private String logMessageSuccess = "FVE-COMPONENT STARTED";

}
