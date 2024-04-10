package io.filipvde.fvdecustomspringbootstartertests.general;

import io.filipvde.commons.SpringLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomSpringBootStarterTestApplication {

	public static void main(String[] args) {
//		SpringApplication.run(CustomSpringBootStarterTestApplication.class, args);
		SpringLauncher.run(CustomSpringBootStarterTestApplication.class, args);
	}

}
