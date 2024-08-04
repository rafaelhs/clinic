import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Patient } from '../models/patient';
import { CommonModule } from '@angular/common';
import { PatientService } from '../services/patient.service';
import { PatientItemComponent } from "../patient-item/patient-item.component";

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [CommonModule, PatientItemComponent],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.css'
})
export class PatientListComponent implements OnInit {
  @Input() patientList: Patient[] = [];
  @Output() patientListChange = new EventEmitter<any[]>();

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