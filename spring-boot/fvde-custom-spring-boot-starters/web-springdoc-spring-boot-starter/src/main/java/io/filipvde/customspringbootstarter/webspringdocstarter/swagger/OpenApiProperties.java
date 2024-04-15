package io.filipvde.customspringbootstarter.webspringdocstarter.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties("microservice.swagger")
public class OpenApiProperties {

    private String controllerPackage;

}
