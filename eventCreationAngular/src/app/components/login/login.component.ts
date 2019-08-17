import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Login } from '../../models/login.model';
import { Person } from '../../models/person.model';
import { LoginService } from '../../../app/services/login.service';

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
  public connectionError = false;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private loginService: LoginService
  ) {
    this.logins = new Login();
    this.loginForm = this.fb.group({
      email: this.fb.control(this.logins.email, [Validators.required]),
      password: this.fb.control(this.logins.password, [Validators.required]),
    });
  }

  ngOnInit() { }

  public submitForm() {
    const newValues = this.loginForm.value;
    const newLogin = new Login();
    newLogin.email = newValues.email;
    newLogin.password = newValues.password;
    this.logins = newLogin;
    this.loginService.postLogin(this.logins).subscribe((person: Person) => {
      this.connection(person);
    });
  }

  public hasLoginError() {
    const control = this.loginForm.get('email');
    return control.errors && control.errors.required && control.invalid;
  }

  public hasPasswordError() {
    const control = this.loginForm.get('password');
    return control.errors && control.errors.required && control.invalid;
  }

  public connection(person: Person) {
    if (person !== null) {
      this.connectionError = false;
      this.router.navigate(['/clientSpace']);
    } else {
      this.connectionError = true;
    }
  }
}
