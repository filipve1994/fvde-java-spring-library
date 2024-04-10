package io.filipvde.commons.beans;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
class WrapperTest {

	@Test
	void test() {
		String value = "fve";
		GenericWrapper<String> wrapper = GenericWrapper.of(value);
		assertEquals(value, wrapper.unwrap(String.class));
		assertEquals(value, wrapper.unwrap());
		assertTrue(wrapper.isWrapperFor(String.class));
	}

}
