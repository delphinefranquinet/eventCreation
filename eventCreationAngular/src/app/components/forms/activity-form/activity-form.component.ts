import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { EventManage } from 'src/app/models/eventManage.model';
import { Activity } from '../../../models/activity.model';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-activity-form',
  templateUrl: './activity-form.component.html',
  styleUrls: ['./activity-form.component.css']
})
export class ActivityFormComponent implements OnInit {

  @Input()
  public title: string;
  @Input() // Always provide one Activity instance! If it is not created yet, provide at least an instance containing the idEvent property.
  public activity: Activity;
  @Input()
  public submitButtonText: string;
  @Input()
  public reactionMessageToUser = '';

  @Output()
  public activitySubmitter = new EventEmitter<Activity>();

  public activityFormComponentIsReady = false;
  public event: EventManage;
  public activityForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private eventService: EventService
  ) { }

  ngOnInit() {
    this.eventService.getEventByID(this.activity.idEvent).subscribe((event: EventManage) => {
      this.event = event;
      if (this.activity.id) { // Should happen if and only if activity already exist. Therefore, probably in case of update by user.
        this.activityForm = this.fb.group({
          name: this.fb.control(this.activity.name, [Validators.required]),
          description: this.fb.control(this.activity.description, [Validators.required]),
          startActivity: this.fb.control(this.activity.startActivity, [Validators.required]),
          endActivity: this.fb.control(this.activity.endActivity, [Validators.required])
        });
      } else { // Should only happen when the activity doesn't already exists and is about to be created by the user.
        this.activityForm = this.fb.group({
          name: this.fb.control('', [Validators.required]),
          description: this.fb.control('', [Validators.required]),
          startActivity: this.fb.control(this.event.startEvent, [Validators.required]), // Already fills the fields with maximum date range
          endActivity: this.fb.control(this.event.endEvent, [Validators.required]) // Same
        });
      }
      this.activityFormComponentIsReady = true;
    });
  }

  public hasNameError() {
    const control = this.activityForm.get('name');
    return control.errors && control.errors.required;
  }

  public hasStartActivityError() {
    const control = this.activityForm.get('startActivity');
    return control.errors && control.errors.required;
  }

  public hasEndActivityError() {
    const control = this.activityForm.get('endActivity');
    return control.errors && control.errors.required;
  }

  public hasDescriptionError() {
    const control = this.activityForm.get('description');
    return control.errors && control.errors.required;
  }

  public submitFormToParent() {
    this.activity.name = this.activityForm.value.name;
    this.activity.description = this.activityForm.value.description;
    this.activity.startActivity = this.activityForm.value.startActivity;
    this.activity.endActivity = this.activityForm.value.endActivity;
    this.activitySubmitter.emit(this.activity);
  }
}
