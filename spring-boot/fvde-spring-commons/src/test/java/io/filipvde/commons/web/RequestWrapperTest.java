package io.filipvde.commons.web;

import io.filipvde.commons.jackson.util.JsonMapperUtils;
import io.filipvde.commons.util.WebUtils;
import jakarta.servlet.ServletInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

/**
 * <p>
 * RequestWrapperTest
 * </p>
 */
class RequestWrapperTest {

	RequestWrapper wrapper;

	@BeforeEach
	void init() throws IOException {
		wrapper = new RequestWrapper(new MockHttpServletRequest());
	}

	@Test
	void setBody() throws IOException {
		Map<String, String> map = Map.of("root", "root");
		wrapper.setBody(JsonMapperUtils.writeValueAsBytes(map));

		ServletInputStream inputStream = wrapper.getInputStream();
		byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
		assertArrayEquals(JsonMapperUtils.writeValueAsBytes(map), bytes);
	}

	@Test
	void addHeader() {
		wrapper.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		wrapper.addHeader(HttpHeaders.USER_AGENT, "test");
		wrapper.addHeader(HttpHeaders.AGE, "20");

		HttpHeaders headers = WebUtils.headers(wrapper);
		assertEquals(MediaType.APPLICATION_JSON_VALUE, headers.getFirst(HttpHeaders.CONTENT_TYPE));
		assertEquals("test", headers.getFirst(HttpHeaders.USER_AGENT));
		assertEquals("20", headers.getFirst(HttpHeaders.AGE));
		assertEquals(3, headers.size());
	}

	@Test
	void putParameter() {
		wrapper.putParameter("username", new String[]{"fve", "root", "admin"});
		wrapper.putParameter("password", "123456");

		MultiValueMap<String, String> params = WebUtils.params(wrapper);
		assertEquals(List.of("fve", "root", "admin"), params.get("username"));
		assertLinesMatch(List.of("123456"), params.get("password"));
		assertEquals(2, params.size());
	}

}
