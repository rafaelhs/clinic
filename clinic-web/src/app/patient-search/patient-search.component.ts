import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
 

@Component({
  selector: 'app-patient-search',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule, MatFormFieldModule, MatInputModule, MatIconModule, MatButtonModule,],
  templateUrl: './patient-search.component.html',
  styleUrl: './patient-search.component.scss'
})
export class PatientSearchComponent implements OnInit{
  @Output() searchPatients = new EventEmitter<any>();
  searchToggle: string = "name";
  orderToggle: string = "ASC";
  query: string = "";


  constructor(
    private router: Router
  ) {
    
  }

  ngOnInit(): void {
    this.handleSearch();
  }

  handleAdd() {
    this.router.navigate(['patient']);
  }

  handleSearch() {
    this.searchPatients.emit({
      query: this.query, 
      searchToggle: this.searchToggle, 
      orderToggle: this.orderToggle});
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
