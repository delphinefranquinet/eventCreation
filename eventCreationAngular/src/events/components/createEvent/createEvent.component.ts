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
      id: this.fb.control(this.event.id),
      name: this.fb.control(this.event.name),
      category: this.fb.control(this.event.category),
      place: this.fb.control(this.event.place),
      date: this.fb.control(this.event.date)

    });
   }


  ngOnInit() {
  }

}
