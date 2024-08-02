package com.example.clinic.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest()
class CpfTest {


    @Test
    void shouldValidateCPF() {
        assertTrue(Cpf.validateCPF("04494119075"));
    }
    @Test
    void ShouldFailUntreatedStringCPF() {
        assertFalse(Cpf.validateCPF("044.941.190-75"));
    }
    @Test
    void shouldFailInvalidCPF() {
        assertFalse(Cpf.validateCPF("04494119070"));
    }

    @Test
    void shouldFailTooShortCPF() {
        assertFalse(Cpf.validateCPF("0449411907"));
    }
    @Test
    void shouldFailTooLongCPF() {
        assertFalse(Cpf.validateCPF("044941190751"));
    }
    @Test
    void ShouldFailKnownInvalidCPF() {
        assertFalse(Cpf.validateCPF("1111111111"));
    }
    @Test
    void ShouldFailKnownInvalidCPF2() {
        assertFalse(Cpf.validateCPF("3333333333"));
    }

}