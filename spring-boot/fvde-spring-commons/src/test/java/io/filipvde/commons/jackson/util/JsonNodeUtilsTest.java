package io.filipvde.commons.jackson.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 * JsonNodeUtilsTest
 * </p>
 */
class JsonNodeUtilsTest {

	static final String json = """
		{
		  "username": "root",
		  "password": "root",
		  "info": {
		    "name": "fve",
		    "email": "1375632510@qq.com"
		  }
		}""";

	static final JsonNode node = JsonMapperUtils.readTree(json);

	@Test
	void findStringValue() {
		String username = JsonNodeUtils.findStringValue(node, "username");
		assertEquals("root", username);
	}

	@Test
	void findValue() {
		Map<String, Object> map = JsonNodeUtils.findValue(node, "info", JsonNodeUtils.STRING_OBJECT_MAP,
			JsonMapper.builder().build());
		assertEquals(Map.of("name", "fve", "email", "1375632510@qq.com"), map);
	}

	@Test
	void findObjectNode() {
		JsonNode jsonNode = JsonNodeUtils.findObjectNode(node, "info");
		assertEquals("fve", jsonNode.get("name").asText());
		assertEquals("1375632510@qq.com", jsonNode.get("email").asText());
	}

	@Test
	void getNodeFirst() {
		JsonNode node = JsonMapperUtils.readTree("""
			{
			    "c": "1",
			    "a": "2",
			    "b": {
			        "c": 3
			    }
			}""");
		assertEquals("1", JsonNodeUtils.findNodeFirst(node, "c").asText());
		assertEquals("2", JsonNodeUtils.findNodeFirst(node, "a").asText());
	}

	@Test
	void getNodeFirstAll() {
		JsonNode node = JsonMapperUtils.readTree("""
			{
			    "c": "1",
			    "a": "2",
			    "b": {
			        "c": "3",
			        "a": "4"
			    }
			}""");
		assertArrayEquals(new String[]{"1", "3"},
			JsonNodeUtils.findNodeAll(node, "c").stream().map(JsonNode::asText).toArray(String[]::new));
		assertArrayEquals(new String[]{"2", "4"},
			JsonNodeUtils.findNodeAll(node, "a").stream().map(JsonNode::asText).toArray(String[]::new));
	}

	@Test
	void getNode() {
		JsonNode node = JsonMapperUtils.readTree("""
			{
			    "c": "1",
			    "a": "2",
			    "b": {
			        "c": "3",
			        "d": {
			            "ab": "6"
			        }
			    },
			    "f": [
			        {
			            "a": "7"
			        },
			        {
			            "a": "8"
			        },
			        {
			            "a": "9"
			        }
			    ]
			}""");
		assertEquals("1", JsonNodeUtils.findNode(node, "c").asText());
		assertEquals("2", JsonNodeUtils.findNode(node, "a").asText());
		assertEquals("3", JsonNodeUtils.findNode(node, "b.c").asText());
		assertEquals("6", JsonNodeUtils.findNode(node, "b.d.ab").asText());
		assertEquals("7", JsonNodeUtils.findNode(node, "f.0.a").asText());
		assertEquals("8", JsonNodeUtils.findNode(node, "f.1.a").asText());
		assertEquals("9", JsonNodeUtils.findNode(node, "f.2.a").asText());
	}

}
