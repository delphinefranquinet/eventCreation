import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from 'src/events/components/home/home.component';
import { CreateEventComponent } from '../events/components/createEvent/createEvent.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from '../events/components/login/login.component';
import { AuthenticationService } from './services/authentication.service.ts.service';
import { AuthGuard } from './guards/auth.guard';
import { SignInLoginComponent } from '../events/components/signInLogin/signInLogin.component';
import { LoginService } from './services/login.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { PersonService } from './services/person.service';
import { EventService } from './services/event.service';
import { ActivityComponent } from '../events/components/activity/activity.component';
import { RegisterComponent } from '../events/components/register/register.component';
import { ActivityService } from './services/activity.service';
import { RegisterService } from './services/register.service';
import { EventComponent } from '../events/components/event/event.component';
import { ClientSpaceComponent } from '../events/components/clientSpace/clientSpace.component';


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
    ClientSpaceComponent
   ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
// tslint:disable-next-line: max-line-length
  providers: [ AuthGuard, AuthenticationService, HttpClient, LoginService, PersonService, EventService, ActivityService, RegisterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
