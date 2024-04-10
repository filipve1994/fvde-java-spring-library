package io.filipvde.commons.util;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 * YamlUtilsTest
 * </p>
 */
class YamlUtilsTest {

	Resource yml = new ClassPathResource("yamlData.yml");

	Map<String, Object> map = Map.of("spring.redis.host", "fve.com", "spring.redis.port", 5672, "spring.env[0]", 1,
		"spring.env[1]", 2);

	@Test
	void convertMapToYaml() throws IOException {
		Map<String, Object> load = new Yaml().load(yml.getInputStream());
		Map<String, Object> result = YamlUtils.convertMapToYaml(map);
		assertEquals(load, result);
	}

	@Test
	void convertYamlToMap() throws IOException {
		Map<String, Object> load = new Yaml().load(yml.getInputStream());
		Properties result = YamlUtils.convertYamlToMap(load);
		assertEquals(map, result);
	}

}
