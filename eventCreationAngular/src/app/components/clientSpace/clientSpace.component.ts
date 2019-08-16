import { Component, OnInit, Input } from '@angular/core';
import { EventService } from '../../services/event.service';
import { EventManage } from '../../models/eventManage.model';
import { Activity } from 'src/app/models/activity.model';
import { ActivityService } from 'src/app/services/activity.service';
import { InscriptionService } from 'src/app/services/inscription.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clientSpace',
  templateUrl: './clientSpace.component.html',
  styleUrls: ['./clientSpace.component.css']
})
export class ClientSpaceComponent implements OnInit {

  public events: EventManage[];
  public activities: Activity[];
  public isDeleted: boolean;
  public inscriptionIsDeleted: string;

  constructor(
    private router: Router,
    private eventService: EventService,
    private activityService: ActivityService,
    private inscriptionService: InscriptionService
  ) { }

  ngOnInit() {
    this.updateAll(false);
  }

  public updateAll(isDeleted: boolean): void {
    this.eventService.getEventsByIdResponsable().subscribe((updatedEvents: EventManage[]) => {
      this.events = updatedEvents;
    }, () => { // If server doesn't responds, it's probably because it hasn't got a session yet.
      this.router.navigate(['/login']); // Using a guard would be better tho...
    });
    this.activityService.getInscriptions().subscribe((activities: Activity[]) => {
      this.activities = activities;
      console.log(activities);
    });
  }

  public unsubscribe(isUnsubscribe: number): any {
    this.inscriptionService.deleteInscription(isUnsubscribe).subscribe((isDeleted: boolean) => {
      if (isDeleted) {
        this.updateAll(false);
        this.inscriptionIsDeleted = 'You are unsubscribed from this activity.';
        console.log('\"response\": \"true\"');
      } else {
        this.inscriptionIsDeleted = 'Unexpected error! Please, contact developers.';
        console.log('UNEXPECTED: \"response\" is not \"true\"!');
      }
    });
  }
}
