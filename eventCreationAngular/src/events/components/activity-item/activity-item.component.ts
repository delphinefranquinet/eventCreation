import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ActivityService } from '../../../app/services/activity.service';

@Component({
  selector: 'app-activity-item',
  templateUrl: './activity-item.component.html',
  styleUrls: ['./activity-item.component.css']
})
export class ActivityItemComponent implements OnInit {

  public activityName: string;
  public activityStart: Date;
  public activityEnd: Date;

  constructor(private route: ActivatedRoute, private activityService: ActivityService) { }

  ngOnInit() {
   /* this.route.params.subscribe(params => {
      const id: string = params.id;

      this.activityService.getActivityByID(id).subscribe(
        activity => {
          this.activityName = activity.name;
          this.activityStart = activity.activityStart;
          this.activityEnd = activity.activityEnd;

        }
        );
      });*/

    }
    }

