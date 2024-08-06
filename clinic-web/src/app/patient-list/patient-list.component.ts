import { Component, OnInit } from '@angular/core';
import { Patient } from '../models/patient';
import { CommonModule } from '@angular/common';
import { PatientService } from '../services/patient.service';
import { PatientItemComponent } from "../patient-item/patient-item.component";
import { PatientSearchComponent } from "../patient-search/patient-search.component";
import { PaginationComponent } from "../pagination/pagination.component";

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [CommonModule, PatientItemComponent, PatientSearchComponent, PaginationComponent],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.css'
})
export class PatientListComponent implements OnInit {
  patientList: Patient[] = [];
  query: string = "";
  searchToggle: string = "name";
  orderToggle: string = "ASC";
  page: number = 0;
  size: number = 2;
  totalElements: number = 0;
  
  constructor(
    private patientServices: PatientService,
  ) { }
  
  ngOnInit(): void {
  }

  handlePageChange(page: number) {
    this.page = page;
    this.patientSearch();
  }

  handleSearchChange(event: any) {
    this.query = event.query;
    this.searchToggle = event.searchToggle;
    this.orderToggle = event.orderToggle;
    this.patientSearch();
  }

  patientSearch() {
    this.patientServices.searchPatients(this.query, this.searchToggle, this.orderToggle, this.page, this.size)
      .subscribe((patients: any) => {
        this.patientList = patients.content;
        this.totalElements = patients.totalElements;
      })
  }
}