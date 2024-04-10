package io.filipvde.customspringbootstarter.encryption;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "microservice.jasypt.encryptor.password")
public class JasyptPasswordProperties {

    private String file = "/jasypt/default";
    private int index = 1;
}
