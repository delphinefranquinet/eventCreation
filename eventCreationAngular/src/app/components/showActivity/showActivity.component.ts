import { Component, OnInit, Input } from '@angular/core';
import { EventManage } from '../../models/eventManage.model';
import { Activity } from '../../models/activity.model';
import { ActivatedRoute } from '@angular/router';
import { ActivityService } from '../../services/activity.service';
import { EventService } from '../../services/event.service';

@Component({
  selector: 'app-showActivity',
  templateUrl: './showActivity.component.html',
  styleUrls: ['./showActivity.component.css']
})

export class ShowActivityComponent implements OnInit {

  public events: EventManage[];
  public eventItem: EventManage;
  public activities: Activity[];
  private connectedUserID: number;

  @Input()
  public eventId = -1;

  constructor(private eventService: EventService, private route: ActivatedRoute, private activityService: ActivityService) { }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.connectedUserID = Number(params.id);
      // TODO: recup ca via une request au back (get idPerson from session ou un truc du genre)!
      console.log('\"typeof params\": ', typeof params);
      console.log('\"JSON.stringify(params)\": ', JSON.stringify(params));
      console.log('\"params.id\": ' + params.id);
      console.log('id of pseudo-connected user is: ' + this.connectedUserID);
    });
    this.updateAll(false);
  }

  public updateAll(isDeleted: boolean): void {
    this.eventService.getEventsByIdResponsable(this.connectedUserID).subscribe((updatedEvents) => {
      this.events = updatedEvents;
    });
    this.activityService.getInscriptions().subscribe((activities: Activity[]) => {
      this.activities = activities;
      console.log(activities);
    });
  }
}
