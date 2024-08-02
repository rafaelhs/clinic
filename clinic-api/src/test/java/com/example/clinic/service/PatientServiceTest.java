package com.example.clinic.service;

import com.example.clinic.model.Patient;
import exception.DocumentAlreadyExistsException;
import exception.EmailAlreadyExistsException;
import exception.InvalidDocumentException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Test
    void shouldCreatePatient() throws DocumentAlreadyExistsException, EmailAlreadyExistsException, InvalidDocumentException {
        Patient patient1 = new Patient(
                null,
                "name1",
                1,
                "document1",
                "111111111111",
                LocalDate.now(),
                "email1@email.com",
                "address1",
                1,
                "district1",
                "complement1",
                "11111111",
                "city1",
                "state1",
                "info1",
                LocalDateTime.now());
        assertNotNull(patientService.createPatient(patient1));
    }

    @Test
    void emailShouldAlreadyExist() throws DocumentAlreadyExistsException, EmailAlreadyExistsException, InvalidDocumentException {
        Patient patient1 = new Patient(
                null,
                "name1",
                1,
                "document21",
                "111111111111",
                LocalDate.now(),
                "teste2@email.com",
                "address1",
                1,
                "district1",
                "complement1",
                "11111111",
                "city1",
                "state1",
                "info1",
                LocalDateTime.now());

        Patient patient2 = new Patient(
                null,
                "name2",
                2,
                "document22",
                "22222222",
                LocalDate.now(),
                "teste2@email.com",
                "address2",
                2,
                "district2",
                "complement2",
                "22222222",
                "city2",
                "state2",
                "info2",
                LocalDateTime.now());
        patientService.createPatient(patient1);
        assertThrows(EmailAlreadyExistsException.class, () -> {
            patientService.createPatient(patient2);
        });
    }

    @Test
    void documentShouldAlreadyExist() throws DocumentAlreadyExistsException, EmailAlreadyExistsException, InvalidDocumentException {
        Patient patient1 = new Patient(
                null,
                "name1",
                1,
                "document31",
                "111111111111",
                LocalDate.now(),
                "teste31@email.com",
                "address1",
                1,
                "district1",
                "complement1",
                "11111111",
                "city1",
                "state1",
                "info1",
                LocalDateTime.now());

        Patient patient2 = new Patient(
                null,
                "name2",
                2,
                "document31",
                "22222222",
                LocalDate.now(),
                "teste32@email.com",
                "address2",
                2,
                "district2",
                "complement2",
                "22222222",
                "city2",
                "state2",
                "info2",
                LocalDateTime.now());
        patientService.createPatient(patient1);
        assertThrows(DocumentAlreadyExistsException.class, () -> {
            patientService.createPatient(patient2);
        });
    }
}