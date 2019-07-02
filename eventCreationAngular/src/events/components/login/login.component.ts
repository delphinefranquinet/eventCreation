import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../app/services/authentication.service.ts.service';
import {  Router } from '@angular/router';
import { Login } from '../../../app/modeles/login.modele';
import { LoginService } from '../../../app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public logins: Login;
  public loginForm: FormGroup;

  constructor(private router: Router, private authService: AuthenticationService, private fb: FormBuilder, private logs: LoginService) {

    this.logins = new Login();

    this.loginForm = this.fb.group({

      login: this.fb.control(this.logins.login, [Validators.required]),
      password: this.fb.control(this.logins.password, [Validators.required]),

    });
  }

  ngOnInit() {
    this.logs.isLogin().subscribe(logins => {

    });
  }
/* btnClick(){
    this.router.navigate(['/createEvent']);
}*/

  public submitForm() {

    const newValues = this.loginForm.value;

    const newLogin = new Login();
    newLogin.login = newValues.login;
    newLogin.password = newValues.password;
    this.logins = newLogin;
    console.log('submit!', this.logins, newValues);

    }

  public hasLoginError() {
    const control = this.loginForm.get('login');
    return control.errors && control.errors.required && !control.invalid;
  }

  public hasPasswordError() {
    const control = this.loginForm.get('password');
    return control.errors && control.errors.required && !control.invalid;
  }
  public isConnect() {
    return this.authService.isConnected();
  }

}
