import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { from } from 'rxjs';
import { Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import {EventManage} from '../../models/eventManage.modele';

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
// tslint:disable-next-line: max-line-length
  constructor(private router: Router, private fb: FormBuilder, private eventService: EventService) {
    this.event = new EventManage();

    this.eventForm = this.fb.group({

      name: this.fb.control(this.event.name, [Validators.required]),
      description: this.fb.control(this.event.description, [Validators.required]),
      startEvent: this.fb.control(this.event.startEvent, [Validators.required]),
      endEvent: this.fb.control(this.event.endEvent, [Validators.required]),
    });
   }


  ngOnInit() {
  }

  next() {
    this.router.navigate(['/activity']);
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

  public submitForm(){
    const newValues = this.eventForm.value;

    const newEvent = new EventManage();

    newEvent.name = newValues.name;
    newEvent.description = newValues.description;
    newEvent.startEvent = newValues.startEvent;
    newEvent.endEvent = newValues.endEvent;
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
