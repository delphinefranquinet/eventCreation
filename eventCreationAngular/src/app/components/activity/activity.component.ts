import { Component, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Activity } from '../../models/activity.model';
import { ActivityService } from '../../services/activity.service';


@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {

  @Output()
  public activity = new Activity();
  @Output()
  public reactionMessageToUser = '';

  public activityComponentIsReady = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private activityService: ActivityService
  ) { }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.activity.idEvent = Number(params.id);
      this.activityComponentIsReady = true;
    });
  }

  public submitNewActivity(activityToCreate: Activity) {
    this.activityService.createActivity(activityToCreate).subscribe((answer: Activity) => {
      if (answer.id) { // Back-end always sends back an activity, but it includes an id property if and only if it has been created in DB
        this.activity = answer; // Is this line really useful?
        this.reactionMessageToUser =
          'Your activity has been created!'
          + '<br />'
          + 'Do you want to create another activity?';
      } else {
        this.reactionMessageToUser =
          'Your activity could not be created! :-('
          + '<br />'
          + 'Make sure the activity interval is within its event period.';
      }
    }, () => {
      this.reactionMessageToUser =
        'Your activity could not be created! :-('
        + '<br />'
        + 'The server isn\'t responding as expected. Please, contact dev team.';
    });
  }
}
