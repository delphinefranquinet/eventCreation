import { Component, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

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
    private router: Router,
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
        alert('Activity seems successfully edited!');
        this.router.navigate(['/showActivity/' + this.activity.idEvent]);
      } else {
        alert(
          'Something strange happenned, please contact developpers :-(\n'
          + 'Make sure the activity interval is within its event period.\n'
          + 'If that is our fault, we will offer you a pizza for compensation.'
        );
      }
    }, () => {
      alert(
        'Your activity could not be edited! :-(\n'
        + 'The server isn\'t responding. That might be either because you entered an unrelevant period,\n'
        + 'or because the back-end developpers were tired (friday evening, you know...).\n'
        + 'Make sure the activity interval is within its event period.'
      );
    });
  }
}
