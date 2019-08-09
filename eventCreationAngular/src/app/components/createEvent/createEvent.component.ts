import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { EventService } from '../../services/event.service';
import { EventManage } from '../../models/eventManage.model';

@Component({
  selector: 'app-createEvent',
  templateUrl: './createEvent.component.html',
  styleUrls: ['./createEvent.component.css']
})
export class CreateEventComponent implements OnInit {

  public event = new EventManage();
  public eventForm: FormGroup;
  public eventError = false;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private eventService: EventService
  ) { }

  ngOnInit() {
    this.eventForm = this.fb.group({
      name: this.fb.control('', [Validators.required]),
      description: this.fb.control('', [Validators.required]),
      place: this.fb.control('', [Validators.required]),
      startEvent: this.fb.control('', [Validators.required]),
      endEvent: this.fb.control('', [Validators.required])
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

  public submitForm() {
    this.event.name = this.eventForm.value.name;
    this.event.description = this.eventForm.value.description;
    this.event.place = this.eventForm.value.place;
    this.event.startEvent = this.eventForm.value.startEvent;
    this.event.endEvent = this.eventForm.value.endEvent;
    this.eventService.postEvent(this.event).subscribe((event) => {
      this.eventForm.reset();
      this.eventError = true;
      this.router.navigate(['/activity']);
    }, () => {
      this.eventError = false;
    });
  }
}
