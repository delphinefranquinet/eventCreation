import { Component, OnInit } from '@angular/core';
import { ActivityService } from '../../../app/services/activity.service';
import { Activity } from '../../../app/modeles/activity.modele';

@Component({
  selector: 'app-signInLogin',
  templateUrl: './signInLogin.component.html',
  styleUrls: ['./signInLogin.component.css']
})
export class SignInLoginComponent implements OnInit {

  public activity: Activity;
  public activities: Activity[];
  constructor( private activityService: ActivityService) { }

  ngOnInit() {
    this.activityService.getActivity().subscribe(activities => this.activities = activities);
  }

}
