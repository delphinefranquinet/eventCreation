import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Event } from 'src/app/modeles/event.modele';
import { from } from 'rxjs';
import { AuthenticationService } from '../../../app/services/authentication.service.ts.service';
import { Router } from '@angular/router';
import { EventService } from '../../../app/services/event.service';

@Component({

  selector: 'app-createEvent',
  templateUrl: './createEvent.component.html',
  styleUrls: ['./createEvent.component.css']
})
export class CreateEventComponent implements OnInit {

  public event: Event;
  public eventForm: FormGroup;
  public now = new Date();
  constructor(private router: Router, private authService: AuthenticationService, private fb: FormBuilder, private eventService: EventService) {
    this.event = new Event();

    this.eventForm = this.fb.group({

      nameOfEvent: this.fb.control(this.event.nameOfEvent, [Validators.required]),
      description: this.fb.control(this.event.descriptionEvent, [Validators.required]),
      begins: this.fb.control(this.event.begins, [Validators.required]),
      ends: this.fb.control(this.event.ends, [Validators.required]),


    });
   }


  ngOnInit() {
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
    const control = this.eventForm.get('description');
    return control.errors && control.errors.required;
  }

  public submitForm(){
    const newValues = this.eventForm.value;

    const newevent = new Event();
    newevent.nameOfEvent = newValues.nameOfEvent;
    newevent.descriptionEvent = newValues.description;
    newevent.begins = newValues.begins;
    newevent.ends = newValues.ends;
    this.event = newevent;
    console.log('submit!', this.event, newValues);
 }
}
