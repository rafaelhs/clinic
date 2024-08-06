import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { PatientService } from '../services/patient.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { Patient } from '../models/patient';
 

@Component({
  selector: 'app-patient-search',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, MatFormFieldModule, MatInputModule, MatIconModule, MatButtonModule,],
  templateUrl: './patient-search.component.html',
  styleUrl: './patient-search.component.scss'
})
export class PatientSearchComponent implements OnInit{
  @Input() patientList: Patient[] = [];
  @Output() patientListChange = new EventEmitter<any[]>();

  query: string = "";
  searchToggle: string = "name";
  orderToggle: string = "ASC";
  page: number = 0;
  size: number = 100;

  constructor(
    private patientServices: PatientService,
    private router: Router
  ) {}

  ngOnInit(): void {
    
  }

  updateContactList(patientList: any[]) {
    this.patientList = patientList;
    this.patientListChange.emit(this.patientList);
  }

  handleAdd() {
    this.router.navigate(['patient']);
  }

  handleSearch() {
    this.patientServices.searchPatients(this.query, this.searchToggle, this.orderToggle, this.page, this.size)
    .subscribe((patients: any) => {
      this.updateContactList(patients.content);
    })
  }

  handleSearchToggle(variable: string) {
    if(this.searchToggle != variable) {
      this.switchSearchToggle();
    } else {
      this.switchOrderToggle();
    }
    this.handleSearch();
  }

  switchSearchToggle() {
    if(this.searchToggle === "createdAt") {
      this.searchToggle = "name";
    } else {
      this.searchToggle = "createdAt";
    }
    this.orderToggle = "ASC";
  }

  switchOrderToggle() {
    if(this.orderToggle === "ASC") {
      this.orderToggle = "DESC";
    } else {
      this.orderToggle = "ASC";
    }
  }



}
