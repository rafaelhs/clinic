package com.example.clinic.controller;

import com.example.clinic.model.ApiError;
import com.example.clinic.model.Patient;
import com.example.clinic.service.PatientService;
import exception.DocumentAlreadyExistsException;
import exception.EmailAlreadyExistsException;
import exception.InvalidDocumentException;
import exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(path = "/")
    public @ResponseBody String ping() {
        return patientService.message();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Object> getPatient(@PathVariable("id") Long id) {
        try{
            Patient res = patientService.getPatient(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(
                    new ApiError(HttpStatus.NOT_FOUND, "not found"),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPatient(@RequestBody Patient patient) {
        try{
            Patient res = patientService.createPatient(patient);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (InvalidDocumentException e) {
            return new ResponseEntity<>(
                    new ApiError(HttpStatus.BAD_REQUEST, "document invalid"),
                    HttpStatus.BAD_REQUEST);
        } catch (EmailAlreadyExistsException e) {
            return new ResponseEntity<>(
                    new ApiError(HttpStatus.CONFLICT, "email in use"),
                    HttpStatus.CONFLICT);
        } catch (DocumentAlreadyExistsException e) {
            return new ResponseEntity<>(
                    new ApiError(HttpStatus.CONFLICT, "document in use"),
                    HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updatePatient(@RequestBody Patient patient) {
        try {
            Patient res = patientService.updatePatient(patient);
            return ResponseEntity.ok().body(res);
        } catch (InvalidDocumentException e) {
            return new ResponseEntity<>(
                    new ApiError(HttpStatus.BAD_REQUEST, "document invalid"),
                    HttpStatus.BAD_REQUEST);
        } catch (EmailAlreadyExistsException e) {
            return new ResponseEntity<>(
                    new ApiError(HttpStatus.CONFLICT, "email in use"),
                    HttpStatus.CONFLICT);
        } catch (DocumentAlreadyExistsException e) {
            return new ResponseEntity<>(
                    new ApiError(HttpStatus.CONFLICT, "document in use"),
                    HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Patient>> getPatientList() {
        List<Patient> res = patientService.listPatient();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeContact(@PathVariable("id") Long id) {
        try{
            Long res = patientService.removeContact(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(
                    new ApiError(HttpStatus.NOT_FOUND, "not found"),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Page<Patient>> searchPatient(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "createdAt") String variable,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction order,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
            ){
        Page<Patient> res = patientService.searchPatient(search, variable, order, page, size);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
