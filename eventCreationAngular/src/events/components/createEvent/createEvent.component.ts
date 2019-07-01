import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Event } from 'src/app/modeles/event.modele';
import { from } from 'rxjs';

@Component({

  selector: 'app-createEvent',
  templateUrl: './createEvent.component.html',
  styleUrls: ['./createEvent.component.css']
})
export class CreateEventComponent implements OnInit {

  public event: Event;
  public eventForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.event = new Event();

    this.eventForm = this.fb.group({

      nameOfEvent: this.fb.control(this.event.nameOfEvent),
      description: this.fb.control(this.event.description),
      begins: this.fb.control(this.event.begins),
      ends: this.fb.control(this.event.ends),


    });
   }


  ngOnInit() {
  }

  public submitForm(){
    const newValues = this.eventForm.value;

    const newevent = new Event();
    newevent.nameOfEvent = newValues.nameOfEvent;
    newevent.description = newValues.description;
    newevent.begins = newValues.begins;
    newevent.ends = newValues.ends;
    this.event = newevent;
    console.log('submit!', this.event, newValues);
 }
}
