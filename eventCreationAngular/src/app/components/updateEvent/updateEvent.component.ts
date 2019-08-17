import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { EventManage } from 'src/app/models/eventManage.model';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-updateEvent',
  templateUrl: './updateEvent.component.html',
  styleUrls: ['./updateEvent.component.css']
})

export class UpdateEventComponent implements OnInit {

  public eventItem = new EventManage();
  public eventForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private eventService: EventService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.eventItem.id = params.id;
      this.eventService.getEventByID(this.eventItem.id).subscribe((selectedEvent: EventManage) => {
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
    this.eventService.updateEvent(this.eventItem).subscribe((isUpdated: boolean) => {
      console.log(isUpdated);
      if (isUpdated) {
        alert('Update Succeeded!');
        this.router.navigate(['/showActivity/' + this.eventItem.id]);
      } else {
        alert('Unable to edit! Makes sure activities times are within event times!');
      }
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
