import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Patient } from '../models/patient';

const url: string = environment.baseUrl;

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) {}

  getPatients() {
    return this.http.get(url + '/patient/list');
  }

  getPatient(id: string) {
    return this.http.get(url + '/patient/' + id);
  }

  createPatient(patient: Patient) {
    return this.http.post(url + '/patient/create', patient);
  }

  updatePatient(patient: Patient) {
    return this.http.post(url + '/patient/update', patient);
  }

  removePatient(id: number) {
    return this.http.delete(url + '/patient/' + id);
  }
}
