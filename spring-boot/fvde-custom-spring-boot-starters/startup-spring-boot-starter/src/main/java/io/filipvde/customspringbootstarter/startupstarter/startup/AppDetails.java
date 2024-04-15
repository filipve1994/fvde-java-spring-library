package io.filipvde.customspringbootstarter.startupstarter.startup;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import static org.springframework.util.Assert.notNull;

@Component
public class AppDetails {

	private static final Logger log = LoggerFactory.getLogger(AppDetails.class);

	@Autowired
	private Environment environment;

	@Setter
	private String port;

	public String getEnvAppName() {
		String projectFinalName = environment.getProperty("spring.application.name");
		notNull(projectFinalName, "spring.application.name cannot be null");
		log.info("projectFinalName : {}", projectFinalName);
		return projectFinalName;
	}

	public String getEnvAppVersion() {
		String projectVersion = environment.getProperty("application.version");
		log.info("projectVersion : {}", projectVersion);
		return projectVersion;

	}

	public String getEnvProfilesActive() {
//		String profileActive = environment.getProperty("spring.profiles.active");
//		log.info("profileActive : {}", profileActive);
//		return profileActive;

		String[] activeProfiles = Optional.of(environment.getActiveProfiles()).orElse(new String[0]);

		String activeProfile = (activeProfiles.length > 0) ? String.join(",", activeProfiles) : "default";

		return activeProfile;
	}

	public String logActiveProfile() {
		return "Active Profile: " + getEnvProfilesActive();
	}

	public String getEnvAppServerContextPath() {
//		String contextPath = environment.getProperty("server.servlet.context-path");
//		contextPath = (contextPath != null) ? contextPath : "/";

		String contextPath = Optional.ofNullable(environment.getProperty("server.servlet.context-path")).orElse("");
		log.info("contextPath : {}", contextPath);
		return contextPath;
	}

	public String getEnvAppServerAddress() {
//		String host = getServerIP();
		String host = Optional.ofNullable(environment.getProperty("server.address")).orElse("localhost");
		log.info("serverAddress : {}", host);
		return host;
	}

	public String getEnvServerPort() {
		log.info("getEnvServerPort : {}", port);
		if (port == null) {
//			String port = environment.getProperty("server.port");
			this.port = Optional.ofNullable(environment.getProperty("server.port")).orElse("8080");
			log.info("getEnvServerPort if check : {}", port);
		}

		return port;
	}

	public String getEnvServerProtocol() {
		String protocol = Optional.ofNullable(environment.getProperty("server.ssl.key-store")).map(key -> "https").orElse("http");
		log.info("protocol : {}", protocol);
		return protocol;
	}

	public String getEnvServerHomeUrl() {
//		String homeUrl = "http://" + serverAddress + ":" + port + contextPath;
		String homeUrl = "http://" + getEnvAppServerAddress() + ":" + getEnvServerPort() + getEnvAppServerContextPath();
		log.info("home: {}", homeUrl);

		return homeUrl;
	}

	public String getSpringdocSwaggerUiPathUrl() {
		String springdocSwaggerUiPathUrl = environment.getProperty("springdoc.swagger-ui.path");
		if (springdocSwaggerUiPathUrl != null) {
//			String swaggerUrl = "http://" + serverAddress + ":" + port + contextPath + springdocSwaggerUiPathUrl;
			String swaggerUrl = "http://" + getEnvAppServerAddress() + ":" + getEnvServerPort() + getEnvAppServerContextPath() + springdocSwaggerUiPathUrl;
			log.info("docs: {}", swaggerUrl);
		} else {
			log.info("docs: {}", "There is no swagger in this project.");
		}

		return springdocSwaggerUiPathUrl;
	}

	public String getSwaggerUI() {
		String swaggerUI = Optional.ofNullable(environment.getProperty("springdoc.swagger-ui.path")).orElse("/swagger-ui/index.html");

		return swaggerUI;
	}

	public String logAccessSwaggerUiUrl() {
		return "Access Swagger UI URL: " + getEnvServerProtocol() + "://" + getEnvAppServerAddress() + ":" + getEnvServerPort() + getEnvAppServerContextPath() + getSwaggerUI();
	}

	public String getH2ConsoleUipath() {
		String h2ConsoleUiPath = Optional.ofNullable(environment.getProperty("spring.h2.console.path")).orElse("/h2-console");

		return h2ConsoleUiPath;
	}

	public boolean checkH2Enabled() {
		String h2Enabled = Optional.ofNullable(environment.getProperty("spring.h2.console.enabled")).orElse("false");
		boolean h2EnabledCheck = h2Enabled.equalsIgnoreCase("true");
		return h2EnabledCheck;
	}

	public String logAccessH2UiUrl() {
		return "Access H2 UI URL: " + getEnvServerProtocol() + "://" + getEnvAppServerAddress() + ":" + getEnvServerPort() + getEnvAppServerContextPath() + getH2ConsoleUipath();
	}


	public String getServerIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();

		} catch (UnknownHostException e) {
			log.error("Error resolving host address", e);
			return "unknown";
		}
	}

	public static String getStartSuccessMessage() {
		return """
			 ____    __                    __        ____                                                  \s
			/\\  _`\\ /\\ \\__                /\\ \\__    /\\  _`\\                                                \s
			\\ \\,\\L\\_\\ \\ ,_\\    __     _ __\\ \\ ,_\\   \\ \\,\\L\\_\\  __  __    ___    ___     __    ____    ____ \s
			 \\/_\\__ \\\\ \\ \\/  /'__`\\  /\\`'__\\ \\ \\/    \\/_\\__ \\ /\\ \\/\\ \\  /'___\\ /'___\\ /'__`\\ /',__\\  /',__\\\s
			   /\\ \\L\\ \\ \\ \\_/\\ \\L\\.\\_\\ \\ \\/ \\ \\ \\_     /\\ \\L\\ \\ \\ \\_\\ \\/\\ \\__//\\ \\__//\\  __//\\__, `\\/\\__, `\\
			   \\ `\\____\\ \\__\\ \\__/.\\_\\\\ \\_\\  \\ \\__\\    \\ `\\____\\ \\____/\\ \\____\\ \\____\\ \\____\\/\\____/\\/\\____/
			    \\/_____/\\/__/\\/__/\\/_/ \\/_/   \\/__/     \\/_____/\\/___/  \\/____/\\/____/\\/____/\\/___/  \\/___/\s
			                                                                                               \s
			                                                                                               \s""";
	}
}
