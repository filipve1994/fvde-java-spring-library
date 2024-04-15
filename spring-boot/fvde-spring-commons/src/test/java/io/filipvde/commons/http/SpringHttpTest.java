package io.filipvde.commons.http;

import io.filipvde.commons.http.annotation.EnableHttpClient;
import io.filipvde.commons.http.annotation.HttpClientType;
import io.filipvde.commons.spring.context.SpringContextHolder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 */
@SpringBootTest("spring.main.web-application-type=servlet")
@EnableHttpClient({HttpClientType.REST_TEMPLATE, HttpClientType.WEB_CLIENT, HttpClientType.REST_CLIENT})
@Disabled
class SpringHttpTest {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	WebClient webClient;

	@Autowired
	RestClient restClient;

	@Test
	void test() {
		assertNotNull(restTemplate);
		assertNotNull(webClient);
		assertNotNull(restClient);
		assertEquals(SpringContextHolder.getBean(RestTemplate.class), restTemplate);
		assertEquals(SpringContextHolder.getBean(WebClient.class), webClient);
		assertEquals(SpringContextHolder.getBean(RestClient.class), restClient);
	}

}
