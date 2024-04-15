package io.filipvde.customspringbootstarter.thymeleafstarter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.nio.charset.StandardCharsets;

@AutoConfiguration
@ConditionalOnClass(name = "org.thymeleaf.spring6.SpringTemplateEngine")
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class ThymeleafConfig {

	/**
	 * Spring template engine bean.
	 *
	 * @return SpringTemplateEngine
	 */
	@Bean
	public SpringTemplateEngine springTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(htmlTemplateResolver());

		return templateEngine;
	}

	/**
	 * Spring resource template resolver bean.
	 *
	 * @return SpringResourceTemplateResolver
	 */
	@Bean
	public SpringResourceTemplateResolver htmlTemplateResolver() {
		SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
//        emailTemplateResolver.setPrefix("classpath:/templates/");
		emailTemplateResolver.setPrefix("classpath:/templates/thymeleaf");
		emailTemplateResolver.setSuffix(".html");
		emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
		emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());

		return emailTemplateResolver;
	}

}
