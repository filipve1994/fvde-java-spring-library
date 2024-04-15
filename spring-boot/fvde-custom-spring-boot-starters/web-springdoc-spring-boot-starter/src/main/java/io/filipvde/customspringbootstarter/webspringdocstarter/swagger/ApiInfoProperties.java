package io.filipvde.customspringbootstarter.webspringdocstarter.swagger;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@ConfigurationProperties("microservice.swagger.api-info")
@Setter
@Getter
@ToString
public class ApiInfoProperties {

	private static final Logger log = LoggerFactory.getLogger(ApiInfoProperties.class);


	private String title;
    private String description;
    private String termsOfServiceUrl;
    private String licenseName;
    private String licenseUrl;

    private Optional<BuildProperties> buildPropertiesOpt;

	private String contactName;
	private String contactUrl;
	private String contactEmail;

    /**
     * On a pristine checkout the build.properties is not yet present (made by Maven build)
     * so lets not depend on it as it is only meant for the swagger definition
     **/
    @Autowired
    public ApiInfoProperties(@Nonnull final Optional<BuildProperties> buildPropertiesOpt) {
        this.buildPropertiesOpt = buildPropertiesOpt;
    }

    @Nonnull
    public Info buildApiInfo() {
        String version = buildPropertiesOpt.map(BuildProperties::getVersion).orElse("(unk)");

        Info info = new Info();
		log.debug("title is : " + title);
		log.debug("description is : " + description);
		log.debug("version is : " + version);
		log.debug("termsOfServiceUrl is : " + termsOfServiceUrl);
        info.setTitle(title);
        info.setDescription(description);
        info.setVersion(version);
        info.setTermsOfService(termsOfServiceUrl);

        License license = new License();
		log.debug("licenseName is : " + licenseName);
		log.debug("licenseUrl is : " + licenseUrl);
		license.setName(licenseName);
        license.setUrl(licenseUrl);

        info.setLicense(license);

		Contact contact = new Contact();
		log.debug("contactName is : " + contactName);
		log.debug("contactUrl is : " + contactUrl);
		log.debug("contactEmail is : " + contactEmail);
		contact.setName(contactName);
		contact.setUrl(contactUrl);
		contact.setEmail(contactEmail);

        info.setContact(contact);

        return info;
    }
}