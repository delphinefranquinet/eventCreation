import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateEventComponent } from './components/createEvent/createEvent.component';
import { LoginComponent } from '../app/components/login/login.component';
import { SignInLoginComponent } from './components/signInLogin/signInLogin.component';
import { ActivityComponent } from './components/activity/activity.component';
import { RegisterComponent } from './components/register/register.component';
import { EventComponent } from './components/event/event.component';
import { ActivityInscriptionComponent } from './components/inscription/activityInscription.component';
import { HomeComponent } from './components/home/home.component';
import { ClientSpaceComponent } from './components/clientSpace/clientSpace.component';



const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signInLogin', component: SignInLoginComponent },
  { path: 'createEvent', component: CreateEventComponent },
  { path: 'activity', component: ActivityComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'activityInscription/:id', component: ActivityInscriptionComponent },
  { path: 'event/:id', component: EventComponent },
  { path: 'clientSpace', component: ClientSpaceComponent},
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
