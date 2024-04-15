package io.filipvde.customspringbootstarter.microservicestarter.internationalization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

	private static final Logger log = LoggerFactory.getLogger(MessageConfiguration.class);


	public static final String DEFAULT_ENCODING = "UTF-8";

//	@Bean
//	MessageSource generalMessageSource() {
//
//		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("classpath:/messages/general/GeneralMessages");
//		messageSource.setDefaultEncoding(DEFAULT_ENCODING);
//
//		return messageSource;
//	}
//
//	@Bean
//	MessageSource exceptionMessageSource() {
//
//		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("classpath:/messages/exception/ExceptionMessages");
//		messageSource.setDefaultEncoding(DEFAULT_ENCODING);
//
//		return messageSource;
//	}
//
//	@Bean
//	public MessageSource validationMessageSource() {
//
//		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("classpath:/messages/validation/ValidationMessages");
//		messageSource.setDefaultEncoding(DEFAULT_ENCODING);
//
//		return messageSource;
//	}
//
//	@Bean
//	public LocalValidatorFactoryBean getValidator() {
//
//		final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//		bean.setValidationMessageSource(validationMessageSource());
//
//		return bean;
//	}
}
