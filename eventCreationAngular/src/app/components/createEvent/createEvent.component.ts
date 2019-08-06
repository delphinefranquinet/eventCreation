import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { from } from 'rxjs';
import { Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { EventManage } from '../../models/eventManage.model';

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

  @Input() eventz: EventManage;

  constructor(private router: Router,
              private fb: FormBuilder,
              private eventService: EventService) {
    this.event = new EventManage();

    this.eventForm = this.fb.group({

      name: this.fb.control(this.event.name, [Validators.required]),
      description: this.fb.control(this.event.description, [Validators.required]),
      place: this.fb.control(this.event.place, [Validators.required]),
      startEvent: this.fb.control(this.event.startEvent, [Validators.required]),
      endEvent: this.fb.control(this.event.endEvent, [Validators.required]),
    });
  }


  ngOnInit() {
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
    const newValues = this.eventForm.value;

    const newEvent = new EventManage();

    newEvent.name = newValues.name;
    newEvent.description = newValues.description;
    newEvent.place = newValues.place;
    newEvent.startEvent = newValues.startEvent;
    newEvent.endEvent = newValues.endEvent;
    this.event = newEvent;

    this.eventService

      .postEvent(this.event).subscribe
      /* postlogin envoie un observale / observable =
       la prochaine fois qu'un evenement aura lieu, exécute ca ...(person => console.log(person));
     */
      (event => {
        this.eventForm.reset();
        this.eventError = true;
        this.router.navigate(['/activity']);
      },
        () => { this.eventError = false; });
  }

}
