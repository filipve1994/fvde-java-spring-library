package io.filipvde.commons.util;

import com.fasterxml.jackson.core.type.TypeReference;
import io.filipvde.commons.jackson.util.JsonMapperUtils;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * <p>
 * PairTest
 * </p>
 */
class PairTest {

	static Pair<String, Integer> pair = Pair.of("fve", 123456);

	@Test
	void toMap() {
		assertEquals(pair.toMap(), Map.of("fve", 123456));
		assertEquals(Map.entry("fve", 123456), pair.toEntry());
	}

	@Test
	void map() {
		Pair<String, String> map = pair.map(String::toUpperCase, String::valueOf);
		assertEquals("FVE", map.key());
		assertEquals("123456", map.value());

		Pair<String, Integer> keyMap = pair.keyMap(String::toUpperCase);
		assertEquals("FVE", keyMap.key());
		assertEquals(123456, keyMap.value());

		Pair<String, String> valueMap = pair.valueMap(String::valueOf);
		assertEquals("fve", valueMap.key());
		assertEquals("123456", valueMap.value());

		Pair<String, String> flatMap = pair.flatMap((key, value) -> Pair.of(key.toUpperCase(), String.valueOf(value)));
		assertEquals("FVE", flatMap.key());
		assertEquals("123456", flatMap.value());
	}

	@Test
	void pairJsonSerializerTest() {
		String json = "{\"fve\":123456}";
		String result = JsonMapperUtils.writeValueAsString(pair);
		assertEquals(json, result);
	}

	@Test
	void pairJsonDeserializerTest() {

		Pair<String, Integer> empty = JsonMapperUtils.readValue("{}", new TypeReference<>() {
		});
		assertNull(empty.key());
		assertNull(empty.value());
		assertEquals(Pair.EMPTY, empty);

		@Language("JSON")
		String json = "{\"fve\":123456}";
		Pair<String, Integer> result = JsonMapperUtils.readValue(json, new TypeReference<>() {
		});
		assertEquals("fve", result.key());
		assertEquals(123456, result.value());
		assertEquals(pair, result);

		@Language("JSON")
		String json2 = """
			{"fve": {"root": "username"}}""";
		Pair<String, Pair<String, String>> result2 = JsonMapperUtils.readValue(json2, new TypeReference<>() {
		});
		assertEquals("fve", result2.key());
		assertEquals("root", result2.value().key());
		assertEquals("username", result2.value().value());

		@Language("JSON")
		String json3 = """
			{"fve":  [1,2,3]}""";
		Pair<String, List<Integer>> result3 = JsonMapperUtils.readValue(json3, new TypeReference<>() {
		});
		assertEquals("fve", result3.key());
		assertEquals(List.of(1, 2, 3), result3.value());

		@Language("JSON")
		String json4 = """
			{
			  "fve": {
			    "username": "root",
			    "password": "root"
			  }
			}""";
		Pair<String, Map<String, String>> result4 = JsonMapperUtils.readValue(json4, new TypeReference<>() {
		});
		assertEquals("fve", result4.key());
		assertEquals(Map.of("username", "root", "password", "root"), result4.value());
	}

}
