import { Component, OnInit } from '@angular/core';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Activity } from '../../../app/modeles/activity.modele';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { ActivityService } from '../../../app/services/activity.service';

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

// tslint:disable-next-line: max-line-length
  constructor(private router: Router, private fb: FormBuilder, private activityService: ActivityService) {
  this.activity = new Activity();

  this.activityForm = this.fb.group({

    name: this.fb.control(this.activity.name, [Validators.required]),
    description: this.fb.control(this.activity.description, [Validators.required]),
    startActivity: this.fb.control(this.activity.startActivity, [Validators.required]),
    endActivity: this.fb.control(this.activity.endActivity, [Validators.required]),
  });
 }


  ngOnInit() {
  }

  next() {
    this.router.navigate(['/clientSpace']);
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

  public submitForm(){
    const newValues = this.activityForm.value;

    const newActivity = new Activity();

    newActivity.name = newValues.name;
    newActivity.description = newValues.description;
    newActivity.startActivity = newValues.startActivity;
    newActivity.endActivity = newValues.endActivity;
    this.activity = newActivity;

    this.activityService
// tslint:disable-next-line: max-line-length
    .postActivity(this.activity).subscribe// postlogin envoie un observale / observable =  la prochaine fois qu'un evenement aura lieu, exécute ca ...(person => console.log(person));
    (activity =>  this.connection(activity));
  }


  public connection(event: Activity) {
     if (event === null) {
       this.activityError = true;
     } else {
       this.activityError = false;
       this.next();

    }
  }
}

