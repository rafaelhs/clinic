import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { PatientService } from '../services/patient.service';
import { Patient } from '../models/patient';
import { PatientSearchComponent } from '../patient-search/patient-search.component';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';

@Component({
  selector: 'app-patient-form',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [NgxMaskDirective, NgxMaskPipe, PatientSearchComponent, CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatDatepickerModule, MatButtonModule, MatDividerModule, MatIconModule],
  templateUrl: './patient-form.component.html',
  styleUrl: './patient-form.component.css'
})
export class PatientFormComponent implements OnInit{
  patient: any = new Patient(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);


  constructor(
    private patientServices: PatientService,
    private route: ActivatedRoute,
    private router: Router
  ){ }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = params.get('id');
      if(id) {
        this.patientServices.getPatient(id).subscribe((patient: any) => {
          this.patient = patient;
        })
      }
      
    })
  }

  handleReturn() {
    this.router.navigate(['patients']);
  }

  handleSave() {
    if(this.patient.id) {
      this.patientServices.updatePatient(this.patient).subscribe(() => {
        this.router.navigate(['patients']);
      })
    } else {
      this.patientServices.createPatient(this.patient).subscribe(() => {
        this.router.navigate(['patients']);
      })
    }
  }

  handleRemove() {
    this.patientServices.removePatient(this.patient.id).subscribe(() => {
      this.router.navigate(['patients']);
    })
  }
}
