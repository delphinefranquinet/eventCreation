import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateEventComponent } from './components/createEvent/createEvent.component';
import { LoginComponent } from '../app/components/login/login.component';
import { ActivityComponent } from './components/activity/activity.component';
import { RegisterComponent } from './components/register/register.component';
import { EventComponent } from './components/event/event.component';
import { ActivityInscriptionComponent } from './components/inscription/activityInscription.component';
import { HomeComponent } from './components/home/home.component';
import { ClientSpaceComponent } from './components/clientSpace/clientSpace.component';
import { UpdateEventComponent } from './components/updateEvent/updateEvent.component';
import { ShowActivityComponent } from './components/showActivity/showActivity.component';



const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'createEvent', component: CreateEventComponent },
  { path: 'activity/:id', component: ActivityComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'activityInscription/:id', component: ActivityInscriptionComponent },
  { path: 'event/:id', component: EventComponent },
  { path: 'clientSpace/:id', component: ClientSpaceComponent },
  { path: 'updateEvent/:id', component: UpdateEventComponent },
  { path: 'showActivity/:id', component: ShowActivityComponent },
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
