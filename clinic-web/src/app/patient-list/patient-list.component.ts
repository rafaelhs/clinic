import { Component, OnInit } from '@angular/core';
import { Patient } from '../models/patient';
import { CommonModule } from '@angular/common';
import { PatientService } from '../services/patient.service';
import { PatientItemComponent } from "../patient-item/patient-item.component";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [CommonModule, PatientItemComponent],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.css'
})
export class PatientListComponent implements OnInit {
  patientList: Patient[] = [];

  constructor(
    private services: PatientService,
  ) { }
  
  ngOnInit(): void {
    this.services.getPatients().subscribe((patients: any) => {
      this.patientList = patients;
    });
  }
}