import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../app/services/authentication.service.ts.service';
import {  Router } from '@angular/router';
import { Login } from '../../../app/modeles/login.modele';
import { LoginService } from '../../../app/services/login.service';
import { Person } from '../../../app/modeles/person.modele';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input()
  public logins: Login;
  public person: Person;
  public loginForm: FormGroup;
  public connectionError: boolean = false;


  constructor(private router: Router, private authService: AuthenticationService, private fb: FormBuilder, private loginService: LoginService) {

    this.logins = new Login();

    this.loginForm = this.fb.group({
      login: this.fb.control(this.logins.login, [Validators.required]),
      password: this.fb.control(this.logins.password, [Validators.required]),

    });
  }

  ngOnInit() {
  }

  next() {
    this.router.navigate(['/home']);
  }

  public submitForm() {
    const newValues = this.loginForm.value;
    const newLogin = new Login();
    newLogin.login = newValues.login;
    newLogin.password = newValues.password;
    this.logins = newLogin;

    this.loginService
    .postLogin(this.logins).subscribe//postlogin envoie un observale / observable =  la prochaine fois qu'un evenement aura lieu, exécute ca ...(person => console.log(person));
     (person =>  this.connection(person));
    }

  public hasLoginError() {
    const control = this.loginForm.get('login');
    return control.errors && control.errors.required && control.invalid;
  }

  public hasPasswordError() {
    const control = this.loginForm.get('password');
    return control.errors && control.errors.required && control.invalid;
  }

  public isConnect() {
    return this.authService.isConnected();
  }

  public connection(person: Person) {
    if (person === null) {
     this.connectionError = true;
    } else {
      this.connectionError = false;
      this.next();

    }
      }
}
