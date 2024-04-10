package io.filipvde.commons.util;

import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiValueMapSplitterTest {

	@Test
	void split() {
		{
			String param = "names=admin&names=root&password=123456";
			MultiValueMap<String, String> valueMap = MultiValueMapSplitter.of("&", "=").split(param);

			Map<String, List<String>> map = Map.of("names", List.of("admin", "root"), "password", List.of("123456"));

			assertEquals(CollectionUtils.toMultiValueMap(map), valueMap);
		}

		{
			String str = "root=1,2,3&root=4&a=b&a=c";
			Map<String, List<String>> map = Map.of("root", List.of("1", "2", "3", "4"), "a", List.of("b", "c"));
			MultiValueMap<String, String> multiValueMap = MultiValueMapSplitter.of("&", "=").split(str, ",");
			assertEquals(CollectionUtils.toMultiValueMap(map), multiValueMap);
		}
	}

}
