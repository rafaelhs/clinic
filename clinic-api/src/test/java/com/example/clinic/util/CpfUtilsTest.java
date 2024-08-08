package com.example.clinic.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest()
class CpfUtilsTest {


    @Test
    void shouldValidateCPF() {
        assertTrue(CpfUtils.validateCPF("04494119075"));
    }
    @Test
    void ShouldFailUntreatedStringCPF() {
        assertFalse(CpfUtils.validateCPF("044.941.190-75"));
    }
    @Test
    void ShouldFailIfEmptyCPF() {
        assertFalse(CpfUtils.validateCPF(""));
    }
    @Test
    void ShouldFailIfNull() {
        assertFalse(CpfUtils.validateCPF(null));
    }
    @Test
    void ShouldFailIfNotNumbersCPF() {
        assertFalse(CpfUtils.validateCPF("0449A119075"));
    }
    @Test
    void shouldFailInvalidCPF() {
        assertFalse(CpfUtils.validateCPF("04494119070"));
    }

    @Test
    void shouldFailTooShortCPF() {
        assertFalse(CpfUtils.validateCPF("0449411907"));
    }
    @Test
    void shouldFailTooLongCPF() {
        assertFalse(CpfUtils.validateCPF("044941190751"));
    }
    @Test
    void ShouldFailKnownInvalidCPF() {
        assertFalse(CpfUtils.validateCPF("1111111111"));
    }
    @Test
    void ShouldFailKnownInvalidCPF2() {
        assertFalse(CpfUtils.validateCPF("3333333333"));
    }

}