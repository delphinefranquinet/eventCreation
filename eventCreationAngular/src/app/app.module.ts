import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { RegisterService } from './services/register.service';
import { LoginService } from './services/login.service';
import { PersonService } from './services/person.service';
import { EventService } from './services/event.service';
import { ActivityService } from './services/activity.service';

import { HomeComponent } from './components/home/home.component';
import { EventComponent } from './components/event/event.component';
import { RegisterComponent } from './components/register/register.component';
import { SignInLoginComponent } from './components/signInLogin/signInLogin.component';
import { LoginComponent } from './components/login/login.component';
import { ActivityInscriptionComponent } from './components/inscription/activityInscription.component';
import { ClientSpaceComponent } from './components/clientSpace/clientSpace.component';
import { CreateEventComponent } from './components/createEvent/createEvent.component';
import { UpdateEventComponent } from './components/updateEvent/updateEvent.component';
import { ActivityFormComponent } from './components/forms/activity-form/activity-form.component';
import { ActivityComponent } from './components/activity/activity.component';
import { UpdateActivityComponent } from './components/update-activity/update-activity.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    EventComponent,
    RegisterComponent,
    SignInLoginComponent,
    LoginComponent,
    ActivityInscriptionComponent,
    ClientSpaceComponent,
    CreateEventComponent,
    UpdateEventComponent,
    ActivityFormComponent,
    ActivityComponent,
    UpdateActivityComponent
   ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    HttpClient,
    RegisterService,
    LoginService,
    PersonService,
    EventService,
    ActivityService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
