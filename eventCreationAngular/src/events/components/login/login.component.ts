import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Contact } from '../../../app/modeles/contact.modele';
import { AuthenticationService } from '../../../app/services/authentication.service.ts.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public contact: Contact;
  public contactForm: FormGroup;

  constructor(private authService: AuthenticationService,  private router: ActivatedRoute) { }

  ngOnInit() {
  }

  public submitForm(){
    console.log('submit!', this.contact);
  }

  public hasNameError() {
    const control = this.contactForm.get('name');
    return control.errors && control.errors.required;
  }

  public hasFirstNameError() {
    const control = this.contactForm.get('firstName');
    return control.errors && control.errors.required;
  }
  public isConnect() {
    return this.authService.isConnected();
  }
}
