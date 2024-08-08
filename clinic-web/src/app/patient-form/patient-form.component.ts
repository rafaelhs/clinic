import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { PatientService } from '../services/patient.service';
import { Patient } from '../models/patient';
import { PatientSearchComponent } from '../patient-search/patient-search.component';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask'
import { isCPF } from '../Validators/cpf.validator';

@Component({
  selector: 'app-patient-form',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [NgxMaskDirective, NgxMaskPipe, PatientSearchComponent, CommonModule, FormsModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatDatepickerModule, MatButtonModule, MatDividerModule, MatIconModule],
  templateUrl: './patient-form.component.html',
  styleUrl: './patient-form.component.css'
})
export class PatientFormComponent implements OnInit{
  patient: any = new Patient(null, null, null, null, "", null, null, null, null, null, null, null, null, null, null, null);

  patientForm: FormGroup = new FormGroup({
    id: new FormControl(null),
    name: new FormControl(null, [Validators.required]),
    sex: new FormControl(null, [Validators.required]),
    dateOfBirth: new FormControl(null, [Validators.required]),
    document: new FormControl(null, <any>[Validators.required, isCPF]),
    phone: new FormControl(null, [Validators.required]),
    email: new FormControl(null, <any>[Validators.required, Validators.email]),
    zip: new FormControl(null),
    address: new FormControl(null),
    number: new FormControl(null),
    district: new FormControl(null),
    complement: new FormControl(null),
    city: new FormControl(null),
    state: new FormControl(null),
    information: new FormControl(null),
  })



  constructor(
    private formBuilder: FormBuilder,
    private patientServices: PatientService,
    private route: ActivatedRoute,
    private router: Router,
  ){ }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = params.get('id');
      if(id) {
        this.patientServices.getPatient(id).subscribe((patient: any) => {
          this.patient = patient;
          this.patientForm = this.formBuilder.group({
            id:[this.patient.id],
            name: [this.patient.name, Validators.required],
            sex: [this.patient.sex, Validators.required],
            dateOfBirth: [this.patient.dateOfBirth, Validators.required],
            document: [this.patient.document, [Validators.required, isCPF]],
            phone: [this.patient.phone, Validators.required],
            email: [this.patient.email, [Validators.required, Validators.email]],
            zip: [this.patient.zip],
            address: [this.patient.address],
            number: [this.patient.number],
            district: [this.patient.district],
            complement: [this.patient.complement],
            city: [this.patient.city],
            state: [this.patient.state],
            information: [this.patient.information],
          })
        })
      }
    })

  }

  

  handleReturn() {
    this.router.navigate(['patients']);
  }

  handleCreate() {
    let patient = {...this.patientForm.value};
    this.patientServices.createPatient(patient).subscribe({
      next: () => {
      this.router.navigate(['patients']);
      },
      error: (data) => {
        if(data.error.message === "document invalid") {
          this.patientForm.get('document')!.setErrors({ cpf: true});
        }else if(data.error.message === "email invalid") {
          this.patientForm.get('document')!.setErrors({ email: true});
        } else if(data.error.message === "document in use") {
          this.patientForm.get('document')!.setErrors({ inUse: true});
        } else if(data.error.message === "email  in use") {
          this.patientForm.get('email')!.setErrors({ inUse: true});
        }
      }
    })
  }

  handleUpdate() {
    let patient = {...this.patientForm.value};
    this.patientServices.updatePatient(patient).subscribe({
      next: () => {
      this.router.navigate(['patients']);
      },
      error: (data) => {
        if(data.error.message === "document invalid") {
          this.patientForm.get('document')!.setErrors({ cpf: true});
        }else if(data.error.message === "email invalid") {
          this.patientForm.get('document')!.setErrors({ email: true});
        } else if(data.error.message === "document in use") {
          this.patientForm.get('document')!.setErrors({ inUse: true});
        } else if(data.error.message === "email in use") {
          this.patientForm.get('email')!.setErrors({ inUse: true});
        }
      }
    })
  }

  handleRemove() {
    let patient = {...this.patientForm.value};
    this.patientServices.removePatient(patient.id).subscribe(() => {
      this.router.navigate(['patients']);
    })
  }

  handleSubmit() {
    if(!this.patientForm.invalid){
      if(this.patientForm.get("id") && this.patientForm.get("id")?.value != null){
        this.handleUpdate();
      } else {
        this.handleCreate();
    }
  } else {
    window.scroll({ 
      top: 0, 
      left: 0, 
      behavior: 'smooth' 
    });
  }
  }
}
