import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { EventService } from 'src/app/services/event.service';
import { EventManage } from 'src/app/models/eventManage.model';

@Component({
  selector: 'app-updateEvent',
  templateUrl: './updateEvent.component.html',
  styleUrls: ['./updateEvent.component.css']
})
export class UpdateEventComponent implements OnInit {

  public eventId: number;
  public eventItem: EventManage;
  public eventForm: FormGroup;
  public updateError = false;

  constructor(private route: ActivatedRoute, private eventService: EventService, private fb: FormBuilder) { }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.eventId = params.id;
      this.eventService.getEventByID(this.eventId).subscribe((selectedEvent: EventManage) => {
        this.eventItem = selectedEvent;
        this.eventForm = this.fb.group({
          name: this.fb.control(this.eventItem.name, [Validators.required]),
          description: this.fb.control(this.eventItem.description, [Validators.required]),
          place: this.fb.control(this.eventItem.place, [Validators.required]),
          startEvent: this.fb.control(this.eventItem.startEvent, [Validators.required]),
          endEvent: this.fb.control(this.eventItem.endEvent, [Validators.required])
        });
      });
    });
  }

  public submitForm() {
    this.eventItem.name = this.eventForm.value.name;
    this.eventItem.description = this.eventForm.value.description;
    this.eventItem.place = this.eventForm.value.place;
    this.eventItem.startEvent = this.eventForm.value.startEvent;
    this.eventItem.endEvent = this.eventForm.value.endEvent;
    this.eventService.postEvent(this.eventItem).subscribe((event) => {
        this.eventForm.reset();
        this.updateError = true;
      });
  }

  public hasNameError() {
    const control = this.eventForm.get('name');
    return control.errors && control.errors.required;
  }

  public hasStartEventError() {
    const control = this.eventForm.get('startEvent');
    return control.errors && control.errors.required;
  }

  public hasEndEventError() {
    const control = this.eventForm.get('endEvent');
    return control.errors && control.errors.required;
  }

  public hasDescriptionError() {
    const control = this.eventForm.get('description');
    return control.errors && control.errors.required;
  }

  public hasPlaceEventError() {
    const control = this.eventForm.get('place');
    return control.errors && control.errors.required;
  }
}
