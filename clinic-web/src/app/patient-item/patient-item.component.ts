import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-item',
  standalone: true,
  imports: [MatCardModule, MatButtonModule],
  templateUrl: './patient-item.component.html',
  styleUrl: './patient-item.component.css'
})
export class PatientItemComponent implements OnInit{
  @Input() patient: any;
  @Output() patientChange = new EventEmitter<any>();

  constructor(private router: Router) {}

  ngOnInit(): void {
  }

  updatePatient(patient: any) {
    this.patient = patient;
    this.patientChange.emit(this.patient);
  }

  handleClick() {
    this.router.navigate(['patient', this.patient.id]);
  }

  documentFormat(document: string){
    return document.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
  }
  
}
