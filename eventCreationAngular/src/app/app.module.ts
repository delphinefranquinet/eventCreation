import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateEventComponent } from './components/createEvent/createEvent.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from '../app/components/login/login.component';
import { SignInLoginComponent } from './components/signInLogin/signInLogin.component';
import { LoginService } from './services/login.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { PersonService } from './services/person.service';
import { EventService } from './services/event.service';
import { ActivityComponent } from './components/activity/activity.component';
import { RegisterComponent } from './components/register/register.component';
import { ActivityService } from './services/activity.service';
import { RegisterService } from './services/register.service';
import { EventComponent } from './components/event/event.component';
import { ClientSpaceComponent } from './components/clientSpace/clientSpace.component';
import { ActivityInscriptionComponent } from './components/inscription/activityInscription.component';
import { HomeComponent } from './components/home/home.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CreateEventComponent,
    LoginComponent,
    SignInLoginComponent,
    ActivityComponent,
    RegisterComponent,
    EventComponent,
    ClientSpaceComponent,
    ActivityInscriptionComponent
   ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
// tslint:disable-next-line: max-line-length
  providers: [HttpClient, LoginService, PersonService, EventService, ActivityService, RegisterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
