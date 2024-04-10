package io.filipvde.customspringbootstarter.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

/**
 * Swagger configuration
 * It is enabled by default. To disable it use below properties:
 * <code>
 * springdoc.swagger-ui.enabled=false
 * springdoc.api-docs.enabled=false
 * </code>
 * NOTE: replace "Template" name in configuration below with necessary project name
 * <p>
 */
@ConditionalOnProperty(name = "springdoc.swagger-ui.enabled", matchIfMissing = true)
//@ConditionalOnProperty("microservice.swagger.controller-package")
@Configuration
@EnableConfigurationProperties({ApiInfoProperties.class, OpenApiProperties.class})
//@PropertySource(value = {"classpath:application.properties", "classpath:custom.properties"})
@PropertySource(value = {"classpath:swagger-default.properties",})
@Slf4j
//public class OpenApiAutoConfiguration implements WebMvcConfigurer {
public class OpenApiAutoConfiguration {

	private final ApiInfoProperties apiInfoProperties;

	private final OpenApiProperties openApiProperties;

	public OpenApiAutoConfiguration(ApiInfoProperties apiInfoProperties, OpenApiProperties openApiProperties) {
		this.apiInfoProperties = apiInfoProperties;
		this.openApiProperties = openApiProperties;
	}

	@Bean
	public OpenAPI openAPI() {
//		final Info apiInformation = apiInfo();
		final Info apiInformation = apiInfoProperties.buildApiInfo();
		log.info("apiInfo: " + apiInformation.toString());

		final Components components = new Components();

		final String schemeName = "bearerAuth";
		components.addSecuritySchemes(
			schemeName,
			new SecurityScheme()
				.name(schemeName)
				.type(HTTP)
				.scheme("bearer")
				.bearerFormat("JWT")
		);

		ExternalDocumentation fveMicroServiceDocumentation = new ExternalDocumentation()
			.description("FVE MicroService Documentation");

		final OpenAPI openAPI = new OpenAPI();
		openAPI.setInfo(apiInformation);
		openAPI.setComponents(components);
		openAPI.addSecurityItem(new SecurityRequirement().addList(schemeName));
		openAPI.setExternalDocs(fveMicroServiceDocumentation);

		return openAPI;

	}

//	@Bean
//	@Nonnull
//	public Info apiInfo() {
//		return apiInfoProperties.buildApiInfo();
//	}

}
