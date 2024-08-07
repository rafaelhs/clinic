import { Routes } from '@angular/router';
import { NotFoundComponent } from './not-found/not-found.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { PatientFormComponent } from './patient-form/patient-form.component';

export const routes: Routes = [
  {path: 'patients', component: PatientListComponent},
  {path: 'patient/:id', component: PatientFormComponent},
  {path: 'patient', component: PatientFormComponent},
  {path: '', component: PatientListComponent},
  {path: '**', component: NotFoundComponent},
];
