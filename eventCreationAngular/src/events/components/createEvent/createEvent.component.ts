import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { from } from 'rxjs';
import { AuthenticationService } from '../../../app/services/authentication.service.ts.service';
import { Router } from '@angular/router';
import { EventService } from '../../../app/services/event.service';
import {EventManage} from '../../../app/modeles/eventManage.modele';

@Component({

  selector: 'app-createEvent',
  templateUrl: './createEvent.component.html',
  styleUrls: ['./createEvent.component.css']
})

export class CreateEventComponent implements OnInit {

  public event: EventManage;
  public eventForm: FormGroup;
  public now = new Date();
  public eventError = false;

// tslint:disable-next-line: max-line-length
  constructor(private router: Router, private authService: AuthenticationService, private fb: FormBuilder, private eventService: EventService) {
    this.event = new EventManage();

    this.eventForm = this.fb.group({

      name: this.fb.control(this.event.name, [Validators.required]),
      description: this.fb.control(this.event.descriptionEvent, [Validators.required]),
      startEvent: this.fb.control(this.event.startEvent, [Validators.required]),
      endEvent: this.fb.control(this.event.endEvent, [Validators.required]),
    });
   }


  ngOnInit() {
  }

  next() {
    this.router.navigate(['/activity']);
  }

  public hasnameOfEventError() {
    const control = this.eventForm.get('nameOfEvent');
    return control.errors && control.errors.required;
  }

  public hasbeginsError() {
    const control = this.eventForm.get('begins');
    return control.errors && control.errors.required;
  }

  public hasendsError() {
    const control = this.eventForm.get('ends');
    return control.errors && control.errors.required;
  }

  public hasdescriptionError() {
    const control = this.eventForm.get('descriptionOfEvent');
    return control.errors && control.errors.required;
  }

  public isConnect() {
    return this.authService.isConnected();
  }

  public submitForm(){
    const newValues = this.eventForm.value;

    const newEvent = new EventManage();

    newEvent.name = newValues.nameOfEvent;
    newEvent.descriptionEvent = newValues.descriptionEvent;
    newEvent.startEvent = newValues.begins;
    newEvent.endEvent = newValues.ends;
    this.event = newEvent;

    this.eventService
// tslint:disable-next-line: max-line-length
    .postEvent(this.event).subscribe// postlogin envoie un observale / observable =  la prochaine fois qu'un evenement aura lieu, exécute ca ...(person => console.log(person));
    (event =>  this.connection(event));
  }


  public connection(event: EventManage) {
     if (event === null) {
       this.eventError = true;
     } else {
       this.eventError = false;
       this.next();

    }
  }
}
