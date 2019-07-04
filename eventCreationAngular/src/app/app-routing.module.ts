import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from 'src/events/components/home/home.component';
import { CreateEventComponent } from '../events/components/createEvent/createEvent.component';
import { LoginComponent } from '../events/components/login/login.component';
import { SinInComponent } from '../events/components/signIn/sinIn.component';
import { SignInLoginComponent } from '../events/components/signInLogin/signInLogin.component';
import { ActivityComponent } from '../events/components/activity/activity.component';
import { InscriptionComponent } from '../events/components/inscription/inscription.component';



const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'home' , component: HomeComponent},
  {path: 'login' , component: LoginComponent},
  {path: 'signIn' , component: SinInComponent},
  {path: 'signInLogin' , component: SignInLoginComponent},
  {path: 'createEvent' , component: CreateEventComponent},
  {path: 'activity' , component: ActivityComponent},
  {path: 'inscription' , component: InscriptionComponent},
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
