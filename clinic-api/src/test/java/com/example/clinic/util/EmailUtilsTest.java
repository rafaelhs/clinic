package com.example.clinic.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class EmailUtilsTest {

    @Test
    void shouldValidateEmail() {
        assertTrue(EmailUtils.validateEmail("test@email.com"));
    }

    @Test
    void shouldValidateNumberEmail() {
        assertTrue(EmailUtils.validateEmail("test1999@email.com"));
    }

    @Test
    void shouldValidateSpecialCharacterEmail() {
        assertTrue(EmailUtils.validateEmail("tes_t@email.com"));
    }

    @Test
    void shouldValidateSpecialCharacterEmail2() {
        assertTrue(EmailUtils.validateEmail("tes-t@email.com"));
    }

    @Test
    void noPrefixShouldFail() {
        assertFalse(EmailUtils.validateEmail("@email.com"));
    }

    @Test
    void noSuffixShouldFail() {
        assertFalse(EmailUtils.validateEmail("test@"));
    }

    @Test
    void invalidCharacterShouldFail() {
        assertFalse(EmailUtils.validateEmail("te*st@email.com"));
    }

    @Test
    void invalidCharacterShouldFail2() {
        assertFalse(EmailUtils.validateEmail("te#st@email.com"));
    }

    @Test
    void invalidWhiteSpaceShouldFail() {
        assertFalse(EmailUtils.validateEmail("te st@email.com"));
    }

    @Test
    void invalidWhiteSpaceShouldFail2() {
        assertFalse(EmailUtils.validateEmail("test@em ail.com"));
    }

}