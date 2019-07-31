import { Component, OnInit } from '@angular/core';

import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from '../../services/register.service';
import { Register } from '../../models/register.modele';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  public register: Register;
  public registerForm: FormGroup;
  public now = new Date();
  public registerError = false;

// tslint:disable-next-line: max-line-length
  constructor(private router: Router, private fb: FormBuilder, private activityService: RegisterService) {
  this.register = new Register();

  this.registerForm = this.fb.group({

    name: this.fb.control(this.register.name, [Validators.required]),
    firstname: this.fb.control(this.register.firstname, [Validators.required]),
    email: this.fb.control(this.register.email, [Validators.required]),
    password: this.fb.control(this.register.password, [Validators.required]),
  });
 }


  ngOnInit() {
  }

  next() {
    this.router.navigate(['/home']);
  }


  public hasNameError() {
    const control = this.registerForm.get('name');
    return control.errors && control.errors.required;
  }

  public hasFrstNameirError() {
    const control = this.registerForm.get('firstname');
    return control.errors && control.errors.required;
  }

  public hasLoginError() {
    const control = this.registerForm.get('login');
    return control.errors && control.errors.required;
  }

  public hasPasswordError() {
    const control = this.registerForm.get('password');
    return control.errors && control.errors.required;
  }


  public submitForm(){
    const newValues = this.registerForm.value;

    const newRegister = new Register();

    newRegister.name = newValues.name;
    newRegister.firstname = newValues.firstname;
    newRegister.email = newValues.email;
    newRegister.password = newValues.password;
    this.register = newRegister;

    this.activityService
// tslint:disable-next-line: max-line-length
    .postActivity(this.register).subscribe// postlogin envoie un observale / observable =  la prochaine fois qu'un evenement aura lieu, exécute ca ...(person => console.log(person));
    (register =>  this.connection(register));
  }


  public connection(event: Register) {
     if (event === null) {
       this.registerError = true;
     } else {
       this.registerError = false;
       this.next();

    }
  }
}
