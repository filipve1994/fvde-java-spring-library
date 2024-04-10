package io.filipvde.commons.util;

import com.fasterxml.jackson.databind.JsonNode;
import io.filipvde.commons.jackson.util.JsonMapperUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

/**
 * <p>
 * WebUtilsTest
 * </p>
 */
class WebUtilsTest {

	MockHttpServletRequest request;

	MockHttpServletResponse response;

	@BeforeEach
	void init() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	void request() {
		ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);
		RequestContextHolder.setRequestAttributes(servletWebRequest);
		HttpServletRequest req = WebUtils.request();
		assertEquals(request, req);
	}

	@Test
	void response() {
		ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);
		RequestContextHolder.setRequestAttributes(servletWebRequest);
		HttpServletResponse resp = WebUtils.response();
		assertEquals(response, resp);
	}

	@Test
	void headers() {
		request.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		request.addHeader(HttpHeaders.USER_AGENT, "test");

		HttpHeaders headers = WebUtils.headers(request);
		assertEquals(MediaType.APPLICATION_JSON_VALUE, headers.getFirst(HttpHeaders.CONTENT_TYPE));
		assertEquals("test", headers.getFirst(HttpHeaders.USER_AGENT));
		assertEquals(2, headers.size());
	}

	@Test
	void attributes() {
		request.setAttribute("username", "fve");
		request.setAttribute("password", "123456");

		Map<String, Object> attributes = WebUtils.attributes(request);
		assertEquals("fve", attributes.get("username"));
		assertEquals("123456", attributes.get("password"));
		assertEquals(2, attributes.size());
	}

	@Test
	void paramMap() {
		request.addParameter("username", "fve", "root", "admin");
		request.addParameter("password", "123456");

		Map<String, String> paramMap = WebUtils.paramMap(request, ",");
		assertEquals("fve,root,admin", paramMap.get("username"));
		assertEquals("123456", paramMap.get("password"));
		assertEquals(2, paramMap.size());
	}

	@Test
	void params() {
		request.addParameter("username", "fve", "root", "admin");
		request.addParameter("password", "123456");

		MultiValueMap<String, String> params = WebUtils.params(request);
		assertEquals(List.of("fve", "root", "admin"), params.get("username"));
		assertLinesMatch(List.of("123456"), params.get("password"));
		assertEquals(2, params.size());
	}

	@Test
	void realIp() {
		assertEquals("127.0.0.1", WebUtils.realIp(request));
	}

	@Test
	void outJson() {
		Map<String, String> result = Map.of("username", "fve", "password", "123456");
		WebUtils.outJson(response, result);

		JsonNode node = JsonMapperUtils.readTree(response.getContentAsByteArray());
		assertEquals("fve", node.get("username").asText());
		assertEquals("123456", node.get("password").asText());
	}

	@Test
	void out() {
		WebUtils.out(response, "out buffer text", MediaType.TEXT_PLAIN_VALUE);

		String result = new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
		assertEquals("out buffer text", result);
	}

}
