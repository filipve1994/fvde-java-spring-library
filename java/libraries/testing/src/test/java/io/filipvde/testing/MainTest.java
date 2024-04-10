package io.filipvde.testing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainTest {
    @Test
    public void testWithAssertJ() {
        String test = "test";
        assertThat(test).isNotNull();
        assertThat(test).isNotBlank();
        assertThat(test).isEqualTo("test");
    }

    @Test
    public void testWithJunit5() {
        String test = "test";
        assertNotNull(test, "String value should not be null");
        assertFalse(test.isEmpty(), "String value should not be empty");
        assertEquals("test", test, "Strings must be equal");
    }
}