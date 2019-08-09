import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { ActivityService } from 'src/app/services/activity.service';
import { InscriptionService } from '../../services/inscription.service';
import { EventManage } from '../../models/eventManage.model';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})

export class EventComponent implements OnInit {

  @Input()
  public editMode = false; // true if and only if parent component tells so
  @Input()
  public eventId = -1;
  @Output()
  public deleted = new EventEmitter<boolean>();
  public eventItem: EventManage;
  public connectionError: boolean;
  public displayEventConfirmButton = false;
  public displayActivityConfirmButton = false;
  public deleteEventButton = 'Delete Event';
  public deleteActivityButton = 'Delete Activity';
  public unableToDeleteMessage = '';

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private inscriptionService: InscriptionService,
    private eventService: EventService,
    private activityService: ActivityService) { }

  ngOnInit() {
    if (this.eventId === -1) {
      this.route.params.subscribe(params => {
        this.eventId = params.id;
        this.subscribeEvent();
      });
    } else {
      this.subscribeEvent();
    }
  }

  private subscribeEvent() {
    this.eventService.getEventByID(this.eventId).subscribe((selectedEvent: EventManage) => {
      this.eventItem = selectedEvent;
    });
  }

  public popupConfirm(eventItemID: number) {
    const confirmed = confirm('Confirm delete');
    if (confirmed) {
      this.deleteEvent(eventItemID);
      this.next(eventItemID);
    }
  }

  public next(eventID) {
    this.router.navigate(['/showActivity', eventID ]);
  }
  public popupConfirmActivity(activityToDelete: number) {
    const confirmed = confirm('Confirm delete');
    if (confirmed) {
      this.deleteActivity(activityToDelete);
    }
  }

  public deleteEvent(eventToDelete: number) {
    console.log('deleteEvent\(' + eventToDelete + '\) triggered!');
    this.eventService.removeEvent(eventToDelete).subscribe((response: boolean) => {
      if (response) {
        this.deleted.emit(true);
        console.log('\"response\": \"true\"');
      } else {
        this.unableToDeleteMessage = 'Unexpected error! Please, contact developers.';
        console.log('UNEXPECTED: \"response\" is not \"true\"!');
      }
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
      this.connectionError = true;
      this.router.navigate(['/activityInscription']);
    }, () => {
      this.connectionError = false;
    });
  }
}
