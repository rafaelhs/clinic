import { Component, Input, OnInit, Output } from '@angular/core';
import { Patient } from '../models/patient';
import { CommonModule } from '@angular/common';
import { PatientService } from '../services/patient.service';
import { Router } from 'express';
import { EventEmitter } from 'node:stream';

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.css'
})
export class PatientListComponent implements OnInit {
  patientList: Patient[] = [];

  constructor(
    private services: PatientService
  ) {
      this.services.getPatients().subscribe((patients: any) => {
        this.patientList = patients;
      });
    }


  ngOnInit(): void {
  }
}