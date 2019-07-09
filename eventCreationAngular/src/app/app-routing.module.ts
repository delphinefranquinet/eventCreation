import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from 'src/events/components/home/home.component';
import { CreateEventComponent } from '../events/components/createEvent/createEvent.component';
import { LoginComponent } from '../events/components/login/login.component';
import { SignInLoginComponent } from '../events/components/signInLogin/signInLogin.component';
import { ActivityComponent } from '../events/components/activity/activity.component';
import { RegisterComponent } from '../events/components/register/register.component';
import { EventComponent } from '../events/components/event/event.component';
import { ActivityInscriptionComponent } from '../events/components/activityInscription/activityInscription.component';



const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'home' , component: HomeComponent},
  {path: 'login' , component: LoginComponent},
  {path: 'signInLogin' , component: SignInLoginComponent},
  {path: 'createEvent' , component: CreateEventComponent},
  {path: 'activity' , component: ActivityComponent},
  {path: 'register' , component:  RegisterComponent},
  {path: 'activityInscription/:id' , component: ActivityInscriptionComponent},
  {path: 'event/:id' , component:  EventComponent},
  {path: '**', redirectTo: '/home'}
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
