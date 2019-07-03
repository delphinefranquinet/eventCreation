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
import { SinInComponent } from '../events/components/signIn/sinIn.component';
import { SignInLoginComponent } from '../events/components/signInLogin/signInLogin.component';
import { LoginService } from './services/login.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { PersonService } from './services/person.service';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CreateEventComponent,
    LoginComponent,
    SinInComponent,
    SignInLoginComponent
   ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ AuthGuard, AuthenticationService, HttpClient, LoginService, PersonService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
