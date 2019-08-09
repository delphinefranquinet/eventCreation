import { Component, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Activity } from '../../models/activity.model';
import { ActivityService } from '../../services/activity.service';

@Component({
  selector: 'app-update-activity',
  templateUrl: './update-activity.component.html',
  styleUrls: ['./update-activity.component.css']
})
export class UpdateActivityComponent implements OnInit {

  @Output()
  public activity = new Activity();
  @Output()
  public reactionMessageToUser = '';

  public updateActivityComponentIsReady = false;

  constructor(
    private route: ActivatedRoute,
    private activityService: ActivityService
  ) { }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.activityService.getOneActivityById(Number(params.id)).subscribe((activity) => {
        this.activity = activity;
        this.updateActivityComponentIsReady = true;
      });
    });
  }

  public submitEditedActivity(editedActivity: Activity) {
    this.activityService.updateActivity(editedActivity).subscribe((answer: Activity) => {
      if (answer.id === editedActivity.id) { // Back-end always sends back the activity if no exception was arised
        this.activity = answer; // Is this line really useful?
        this.reactionMessageToUser =
          'Activity seems successfully edited!'
          + '<br />'
          + 'Do you want to edit it again?'; // TODO: Nope, plutôt rediriger vers clientSpace
      } else {
        this.reactionMessageToUser =
          'Something strange happenned, please contact developpers :-('
          + '<br />'
          + 'Make sure the activity interval is within its event period.'
          + '<br />'
          + 'If that is our fault, we will offer you a pizza for compensation.';
      }
    }, () => {
      this.reactionMessageToUser =
        'Your activity could not be edited! :-('
        + '<br />'
        + 'The server isn\'t responding. That might be either because you entered an unrelevant period,'
        + '<br />'
        + 'or because the back-end developpers were tired (friday evening, you know...).'
        + '<br />'
        + 'Make sure the activity interval is within its event period.';
    });
  }
}
