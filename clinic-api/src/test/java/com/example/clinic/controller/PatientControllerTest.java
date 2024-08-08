package com.example.clinic.controller;

import com.example.clinic.model.Patient;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest()
@AutoConfigureMockMvc
class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PatientService patientService;
    @MockBean
    private PatientRepository patientRepository;

    // Create Tests
    @Test
    void shouldCreatePatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857003");
        patient.setEmail("emailTest@test.com");

        String requestBody = new ObjectMapper().valueToTree(patient).toString();

        when(patientRepository.findOneByDocument(patient.getDocument())).thenReturn(Optional.empty());
        when(patientRepository.findOneByEmail(patient.getEmail())).thenReturn(Optional.empty());
        when(patientRepository.save(patient)).thenReturn(patient);

        this.mockMvc.perform(post("/patient/create")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldThrowDocumentInvalidExceptionCreate() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857002");
        patient.setEmail("emailTest@test.com");

        String requestBody = new ObjectMapper().valueToTree(patient).toString();

        this.mockMvc.perform(post("/patient/create")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    @Test
    void shouldThrowDocumentConflictExceptionCreate() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857003");
        patient.setEmail("emailTest@test.com");

        Patient patient2 = new Patient();
        patient2.setId(Long.valueOf(2));
        patient2.setDocument("95105857003");
        patient2.setEmail("emailTest@test.com");

        String requestBody = new ObjectMapper().valueToTree(patient).toString();

        when(patientRepository.findOneByDocument(patient.getDocument())).thenReturn(Optional.of(patient2));
        this.mockMvc.perform(post("/patient/create")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void shouldThrowEmailConflictExceptionCreate() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857003");
        patient.setEmail("emailTest@test.com");

        Patient patient2 = new Patient();
        patient2.setId(Long.valueOf(2));
        patient2.setDocument("95105857003");
        patient2.setEmail("emailTest@test.com");

        String requestBody = new ObjectMapper().valueToTree(patient).toString();

        when(patientRepository.findOneByEmail(patient.getEmail())).thenReturn(Optional.of(patient2));
        this.mockMvc.perform(post("/patient/create")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    // Update Tests
    @Test
    void shouldUpdatePatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857003");
        patient.setEmail("emailTest@test.com");

        String requestBody = new ObjectMapper().valueToTree(patient).toString();

        when(patientRepository.findOneByDocument(patient.getDocument())).thenReturn(Optional.of(patient));
        when(patientRepository.findOneByEmail(patient.getEmail())).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);

        this.mockMvc.perform(post("/patient/update")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldThrowDocumentInvalidExceptionUpdate() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857002");
        patient.setEmail("emailTest@test.com");

        String requestBody = new ObjectMapper().valueToTree(patient).toString();

        this.mockMvc.perform(post("/patient/update")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    @Test
    void shouldThrowDocumentConflictExceptionUpdate() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857003");
        patient.setEmail("emailTest@test.com");

        Patient patient2 = new Patient();
        patient2.setId(Long.valueOf(2));
        patient2.setDocument("95105857003");
        patient2.setEmail("emailTest@test.com");

        String requestBody = new ObjectMapper().valueToTree(patient).toString();

        when(patientRepository.findOneByDocument(patient.getDocument())).thenReturn(Optional.of(patient2));
        this.mockMvc.perform(post("/patient/update")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void shouldThrowEmailConflictExceptionUpdate() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857003");
        patient.setEmail("emailTest@test.com");

        Patient patient2 = new Patient();
        patient2.setId(Long.valueOf(2));
        patient2.setDocument("95105857003");
        patient2.setEmail("emailTest@test.com");

        String requestBody = new ObjectMapper().valueToTree(patient).toString();

        when(patientRepository.findOneByEmail(patient.getEmail())).thenReturn(Optional.of(patient2));
        this.mockMvc.perform(post("/patient/update")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    // Remove Tests
    @Test
    void shouldRemovePatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857003");
        patient.setEmail("emailTest@test.com");

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        this.mockMvc.perform(delete("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotRemovePatientNotfound() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857003");
        patient.setEmail("emailTest@test.com");

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.empty());
        this.mockMvc.perform(delete("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    // Get Tests
    @Test
    void shouldGetPatient() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857003");
        patient.setEmail("emailTest@test.com");

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        this.mockMvc.perform(get("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotGetPatientNotfound() throws Exception {
        Patient patient = new Patient();
        patient.setId(Long.valueOf(1));
        patient.setDocument("95105857003");
        patient.setEmail("emailTest@test.com");

        when(patientRepository.findById(patient.getId())).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}