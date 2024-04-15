package io.filipvde.customspringbootstarter.startupstarter.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import static io.filipvde.customspringbootstarter.startupstarter.startup.AppDetails.getStartSuccessMessage;

// https://www.baeldung.com/spring-boot-running-port
@Service
public class LogServerDetailsOnStartup {

	private static final Logger log = LoggerFactory.getLogger(LogServerDetailsOnStartup.class);

	@Autowired
	private Environment environment;

	@Autowired
	private AppDetails appDetails;

	@Autowired
	private StartupProperties startupProperties;

	@EventListener
	public void onServletWebServerInitializedEvent(final ServletWebServerInitializedEvent event) {
		log.info("LogServerDetailsOnStartup: onServletWebServerInitializedEvent"); //1. first this
		appDetails.setPort(String.valueOf(event.getWebServer().getPort()));
		log.info("LogServerDetailsOnStartup: onServletWebServerInitializedEvent: port random is: " + appDetails.getEnvServerPort()); // Port 0

		//

		String protocol = appDetails.getEnvServerProtocol();

		String host = appDetails.getEnvAppServerAddress();

		String serverPort = appDetails.getEnvServerPort();

		String contextPath = appDetails.getEnvAppServerContextPath();

		String activeProfile = appDetails.getEnvProfilesActive();

		String swaggerUI = appDetails.getSwaggerUI();

		String h2ConsoleUiPath = appDetails.getH2ConsoleUipath();
		boolean h2EnabledCheck = appDetails.checkH2Enabled();

		log.info("h2ConsoleUiPath: {} - h2enabledcheck: {} ", h2ConsoleUiPath, h2EnabledCheck);

//		String infostr = """
//   			\n
//			""";
//
//		infostr += "Access Swagger UI URL: \n";
//
//		infostr += "Active Profile: {} \n";
//
//
//
//		log.info(infostr, protocol, host, serverPort, contextPath, swaggerUI, activeProfile);
//
//		log.info("---------------");
//
//		log.info(
//			"""
//				\n
//				Access Swagger UI URL: {}://{}:{}{}{}
//				Active Profile: {}
//				""",
//			protocol, host, serverPort, contextPath, swaggerUI,
//			activeProfile
//		);

		if (h2EnabledCheck) {
			log.info(
				"""
					\n
					Access Swagger UI URL: {}://{}:{}{}{}
					Access h2 UI URL: {}://{}:{}{}{}
					Active Profile: {}
					""",
				protocol, host, serverPort, contextPath, swaggerUI,
				protocol, host, serverPort, contextPath, h2ConsoleUiPath,
				activeProfile
			);
		} else {
				log.info(
					"""
						\n
						Access Swagger UI URL: {}://{}:{}{}{}
						Active Profile: {}
						""",
					protocol, host, serverPort, contextPath, swaggerUI,
					activeProfile
				);

		}

	}

	@EventListener
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		log.info("LogServerDetailsOnStartup: onApplicationEvent");
		log.info("ApplicationListener#onApplicationEvent()");
		log.info(startupProperties.getLogMessageSuccess());

		log.info(appDetails.getEnvAppName().toUpperCase() + " project start success...........");
		log.info("\n{}", getStartSuccessMessage());
	}

}