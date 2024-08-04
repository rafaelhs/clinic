import { Routes } from '@angular/router';
import { NotFoundComponent } from './not-found/not-found.component';
import { PatientListComponent } from './patient-list/patient-list.component';

export const routes: Routes = [
  {path: 'patients', component: PatientListComponent},
  {path: '**', component: NotFoundComponent}
];
