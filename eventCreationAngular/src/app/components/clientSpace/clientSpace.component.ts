import { Component, OnInit } from '@angular/core';
import { EventService } from '../../services/event.service';
import { EventManage } from '../../models/eventManage.model';
import { ActivatedRoute } from '@angular/router';
import { Activity } from 'src/app/models/activity.model';
import { ActivityService } from 'src/app/services/activity.service';


@Component({
  selector: 'app-clientSpace',
  templateUrl: './clientSpace.component.html',
  styleUrls: ['./clientSpace.component.css']
})
export class ClientSpaceComponent implements OnInit {

  private connectedUserID: number;
  public events: EventManage[];
  public activities: Activity[];

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
