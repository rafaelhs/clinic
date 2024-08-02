package com.example.clinic.service;

import com.example.clinic.model.Patient;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.util.Cpf;
import exception.DocumentAlreadyExistsException;
import exception.EmailAlreadyExistsException;
import exception.InvalidDocumentException;
import exception.NotFoundException;
import org.hibernate.query.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public String message() {
        return "ping";
    }

    public Patient getPatient(Long id) throws NotFoundException {
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isEmpty()) throw new NotFoundException("Patient not found");
        return patient.get();
    }

    public Patient createPatient(Patient patient) throws InvalidDocumentException, DocumentAlreadyExistsException, EmailAlreadyExistsException {
        if(!Cpf.validateCPF(patient.getDocument())) {
            throw new InvalidDocumentException("Document is invalid");
        }
        if (patientRepository.findOneByDocument(patient.getDocument()).isPresent()) {
            throw new DocumentAlreadyExistsException("Document already exists");
        }
        if (patientRepository.findOneByEmail(patient.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        patient.setCreatedAt(LocalDateTime.now());
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient) throws InvalidDocumentException, DocumentAlreadyExistsException, EmailAlreadyExistsException {
        if(!Cpf.validateCPF(patient.getDocument())) {
            throw new InvalidDocumentException("Document is invalid");
        }
        Optional<Patient> patientAux = patientRepository.findOneByDocument(patient.getDocument());
        if (!patientAux.isPresent() && patientAux.get().getId().equals(patient.getId())) {
            throw new DocumentAlreadyExistsException("Document already exists");
        }
        patientAux = patientRepository.findOneByEmail(patient.getEmail());
        if (!patientAux.isPresent() && patientAux.get().getEmail().equals(patient.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        return patientRepository.save(patient);
    }

    public List<Patient> listPatient() {
        return patientRepository.findAll();
    }

    public Page<Patient> searchPatient(
            String search,
            String variable,
            Sort.Direction order,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size, order, variable);
        return patientRepository.search("%" + search + "%", pageable);
    }

    public Long removeContact(Long id) throws NotFoundException {
        Optional<Patient> patient = patientRepository.findById(id);
        if(!patient.isPresent()) throw new NotFoundException("Patient not found");
        patientRepository.delete(patient.get());
        return id;
    }
}
