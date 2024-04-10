package io.filipvde.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 * ClassUtilsTest
 * </p>
 */
class ClassUtilsTest {

	@Test
	void toClassTest() {
		Class<String> result = ClassUtils.toClass(String.class);
		assertEquals(String.class, result);
	}

}
