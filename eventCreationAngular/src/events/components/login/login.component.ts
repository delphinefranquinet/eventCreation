import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
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

  constructor(private authService: AuthenticationService,  private router: ActivatedRoute, private fb: FormBuilder) {

    this.contact = new Contact();

    this.contactForm = this.fb.group({

      id: this.fb.control(this.contact.id),
      name: this.fb.control(this.contact.name),
      firstName: this.fb.control(this.contact.firstName)
    });
  }

  ngOnInit() {
  }

  public submitForm(){
    console.log('submit!', this.contact);
  }

  public hasIdError() {
    const control = this.contactForm.get('id');
    return control.errors && control.errors.required;
  }

  public hasPasswordError() {
    const control = this.contactForm.get('pwd');
    return control.errors && control.errors.required;
  }
  public isConnect() {
    return this.authService.isConnected();
  }

}
