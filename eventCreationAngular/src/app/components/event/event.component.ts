import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { EventManage } from '../../models/eventManage.model';
import { EventService } from '../../services/event.service';
import { ActivityService } from 'src/app/services/activity.service';
import { InscriptionService } from '../../services/inscription.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})

export class EventComponent implements OnInit {

  @Input()
  public editMode = false; // true if and only if parent component tells so
  @Input()
  public eventItem = new EventManage();
  @Output()
  public deleted = new EventEmitter<boolean>();
  public unableToDeleteMessage = '';
  public eventComponentIsReady = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private inscriptionService: InscriptionService,
    private eventService: EventService,
    private activityService: ActivityService
  ) { }

  ngOnInit() {
    if (isNaN(this.eventItem.id)) {
      this.route.params.subscribe(params => {
        this.eventItem.id = params.id;
        this.subscribeEvent();
      });
    } else {
      this.subscribeEvent();
    }
  }

  private subscribeEvent() {
    this.eventService.getEventByID(this.eventItem.id).subscribe((selectedEvent: EventManage) => {
      this.eventItem = selectedEvent;
      this.eventComponentIsReady = true;
    });
  }

  public popupConfirm(eventItemID: number) {
    const confirmed = confirm('Confirm delete');
    if (confirmed) {
      this.deleteEvent(eventItemID);
    }
  }

  public popupConfirmActivity(activityToDelete: number) {
    const confirmed = confirm('Confirm delete');
    if (confirmed) {
      this.deleteActivity(activityToDelete);
    }
  }

  public deleteEvent(id: number) {
    console.log('deleteEvent(' + id + ') triggered!');
    this.eventService.removeEvent(id).subscribe((response: boolean) => {
      if (response) {
        this.deleted.emit(true);
      } else {
        this.unableToDeleteMessage = 'Unexpected error! Please, contact developers.';
      }
      console.log('8080 replied ' + response);
    });
  }

  public deleteActivity(activityToDelete: number) {
    console.log('deleteActivity\(' + activityToDelete + '\) triggered!');
    this.activityService.removeActivity(activityToDelete).subscribe((response: boolean) => {
      if (response) {
        this.deleted.emit(true);
        console.log('\"response\": \"true\"');
      } else {
        this.unableToDeleteMessage = 'Unexpected error! Please, contact developers.';
        console.log('UNEXPECTED: \"response\" is not \"true\"!');
      }
    });
  }

  public inscription(activityId: number) {
    this.inscriptionService.postInscription(activityId).subscribe((inscription) => {
      this.router.navigate(['/activityInscription']);
    });
  }
}
