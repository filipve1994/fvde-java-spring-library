package io.filipvde.commons.web;

import io.filipvde.commons.jackson.util.JsonMapperUtils;
import io.filipvde.commons.util.WebUtils;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseWrapperTest {

	ResponseWrapper wrapper;

	@Test
	void getResponseData() throws IOException {
		MockHttpServletResponse response = new MockHttpServletResponse();
		Map<String, String> result = Map.of("username", "fve", "password", "123456");
		wrapper = new ResponseWrapper(response);
		WebUtils.outJson(wrapper, result);

		assertArrayEquals(JsonMapperUtils.writeValueAsBytes(result), wrapper.getResponseData());
		assertEquals(JsonMapperUtils.writeValueAsString(result), wrapper.getOutputStream().toString());

		wrapper.setResponseData(JsonMapperUtils.writeValueAsBytes(Map.of("root", "root")));

		assertArrayEquals(JsonMapperUtils.writeValueAsBytes(Map.of("root", "root")), wrapper.getResponseData());
		assertEquals(JsonMapperUtils.writeValueAsString(Map.of("root", "root")), wrapper.getOutputStream().toString());

	}

}
