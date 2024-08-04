import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

const url: string = environment.baseUrl;

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) {}

  getPatients() {
    console.log(url + '/patient/list')
    return this.http.get(url + '/patient/list');
  }

}
