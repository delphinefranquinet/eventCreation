import { Component, OnInit } from '@angular/core';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Activity } from '../../models/activity.modele';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Router, ActivatedRoute } from '@angular/router';
import { ActivityService } from '../../services/activity.service';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {

  public activity: Activity;
  public activityForm: FormGroup;
  public now = new Date();
  public activityError = false;
  public eventId: number;


  constructor(private router: Router, private route: ActivatedRoute, private fb: FormBuilder, private activityService: ActivityService) {
    this.activity = new Activity();

    this.activityForm = this.fb.group({

      name: this.fb.control(this.activity.name, [Validators.required]),
      description: this.fb.control(this.activity.description, [Validators.required]),
      startActivity: this.fb.control(this.activity.startActivity, [Validators.required]),
      endActivity: this.fb.control(this.activity.endActivity, [Validators.required]),
    });
  }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.eventId = Number(params.id);
    });
  }
  /*next(eventId: number) {
    this.router.navigate(['/activity/' + eventId]);
  }*/

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

  public submitForm() {
    const newValues = this.activityForm.value;

    const newActivity = new Activity();

    newActivity.name = newValues.name;
    newActivity.description = newValues.description;
    newActivity.startActivity = newValues.startActivity;
    newActivity.endActivity = newValues.endActivity;
    this.activity = newActivity;

    this.activityService

      .postActivity(this.activity, this.eventId).subscribe
      (activity => {/*200*/
        this.activityForm.reset();
        this.activityError = true;
      }, () => { this.activityError = false; });

  }



}
