import { Component, OnInit } from '@angular/core';

import { Activity } from '../../models/activity.model';
import { ActivityService } from '../../services/activity.service';

@Component({
  selector: 'app-signInLogin',
  templateUrl: './signInLogin.component.html',
  styleUrls: ['./signInLogin.component.css']
})
export class SignInLoginComponent implements OnInit {

  public activity: Activity;
  public activities: Activity[];

  constructor(
    private activityService: ActivityService
  ) { }

  ngOnInit() {
    this.activityService.getActivity().subscribe((activities) => {
      this.activities = activities;
    });
  }

}
