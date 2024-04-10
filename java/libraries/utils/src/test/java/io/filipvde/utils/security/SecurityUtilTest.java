package io.filipvde.utils.security;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecurityUtilTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldDecrypt() {
        // given
        var text = "200263444";
        // when
        var encrypted = SecurityUtil.encrypt(text);
        var decryped = SecurityUtil.decrypt(encrypted);
        // then
        assertEquals("200263444", decryped);
    }
}