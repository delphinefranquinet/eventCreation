import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { NewActivity } from '../../models/newActivity.model';
import { Activity } from '../../models/activity.model';
import { ActivityService } from '../../services/activity.service';


@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {

  public activityToCreate = new NewActivity();
  public activityForm: FormGroup;
  public creationSucceded = false;
  public creationFailed = false;
  public serverError = false;
  public eventId: number;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private activityService: ActivityService
  ) { }

  ngOnInit() {
    this.activityForm = this.fb.group({
      name: this.fb.control('', [Validators.required]),
      description: this.fb.control('', [Validators.required]),
      startActivity: this.fb.control('', [Validators.required]),
      endActivity: this.fb.control('', [Validators.required]),
    });
    this.route.params.subscribe((params) => {
      this.eventId = Number(params.id);
    });
  }

  // next(eventId: number) {
  //   this.router.navigate(['/activity/' + eventId]);
  // }

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
    this.activityToCreate.name = this.activityForm.value.name;
    this.activityToCreate.description = this.activityForm.value.description;
    this.activityToCreate.startActivity = this.activityForm.value.startActivity;
    this.activityToCreate.endActivity = this.activityForm.value.endActivity;
    this.activityToCreate.idEvent = this.eventId;
    this.activityService.createActivity(this.activityToCreate).subscribe((activity: Activity) => {
      if (activity.id) {
        this.activityForm.reset();
        this.creationSucceded = true;
        this.creationFailed = false;
        this.serverError = false;
      } else {
        this.creationSucceded = false;
        this.creationFailed = true;
        this.serverError = false;
      }
    }, () => {
      this.creationSucceded = false;
      this.creationFailed = false;
      this.serverError = true;
    });
  }
}
