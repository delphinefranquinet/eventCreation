import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NewActivity } from '../../models/newActivity.model';
import { Activity } from '../../models/activity.model';
import { ActivityService } from '../../services/activity.service';
import { Action } from 'rxjs/internal/scheduler/Action';

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

  constructor(private router: Router, private route: ActivatedRoute, private fb: FormBuilder, private activityService: ActivityService) {
    this.activityForm = this.fb.group({
      name: this.fb.control('', [Validators.required]),
      description: this.fb.control('', [Validators.required]),
      startActivity: this.fb.control('', [Validators.required]),
      endActivity: this.fb.control('', [Validators.required]),
    });
  }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.eventId = Number(params.id);
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

  public submitForm() {

    this.activityToCreate.name = this.activityForm.value.name;
    this.activityToCreate.description = this.activityForm.value.description;
    this.activityToCreate.startActivity = this.activityForm.value.startActivity;
    this.activityToCreate.endActivity = this.activityForm.value.endActivity;
    this.activityService.createActivity(this.activityToCreate, this.eventId).subscribe((activity: Activity) => {
      /*200*/
      if (activity.id) {
        this.activityForm.reset();
        alert('Your activity has been created !Do you want to create another activity?');
      } else {
        alert(' Your activity could not be created! Make sure the activity interval is within the event period.');
      }
    }, () => {
      this.serverError = true;
      alert(' Your activity could not be created! The server isn\'t responding as expected. Please, contact dev team.');
    });
  }
}
