import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './components/home/home.component';
import { EventComponent } from './components/event/event.component';
import { RegisterComponent } from './components/register/register.component';
import { SignInLoginComponent } from './components/signInLogin/signInLogin.component';
import { LoginComponent } from './components/login/login.component';
import { ActivityInscriptionComponent } from './components/inscription/activityInscription.component';
import { ClientSpaceComponent } from './components/clientSpace/clientSpace.component';
import { CreateEventComponent } from './components/createEvent/createEvent.component';
import { UpdateEventComponent } from './components/updateEvent/updateEvent.component';
import { ActivityComponent } from './components/activity/activity.component';
import { UpdateActivityComponent } from './components/update-activity/update-activity.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'event/:id', component: EventComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'signInLogin', component: SignInLoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'activityInscription/:id', component: ActivityInscriptionComponent },
  { path: 'clientSpace/:id', component: ClientSpaceComponent },
  { path: 'createEvent', component: CreateEventComponent },
  { path: 'updateEvent/:id', component: UpdateEventComponent },
  { path: 'activity/:id', component: ActivityComponent },
  { path: 'updateActivity/:id', component: UpdateActivityComponent },
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
  ],
  exports: [
    RouterModule
  ],
  declarations: [
  ]
})
export class AppRoutingModule { }
