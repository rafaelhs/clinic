package com.example.clinic.controller;

import com.example.clinic.model.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "patient")
public class PatientController {

    @GetMapping
    public String getPatient() {
        return "ping";
    }
}
