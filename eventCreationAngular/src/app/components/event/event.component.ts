import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { InscriptionService } from '../../services/inscription.service';
import { EventManage } from 'src/app/models/eventManage.modele';

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
  public displayConfirmButton = false;
  public UnableToDeleteMessage = '';

  // public activityError: boolean; // to Zahraa: What for?
  // (I deleted others, check previous commit)

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private inscriptionService: InscriptionService,
    private eventService: EventService
  ) { }

  ngOnInit() {
    if(this.eventId === -1) {
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

  /****************************************************************************************************
  // From Zahraa's commit on Aug 2nd, 16:14 - but I commented it as I don't know what it is used for //
  ****************************************************************************************************/
  // public get route(): ActivatedRoute {
  //   return this._route;
  // }
  // public set route(value: ActivatedRoute) {
  //   this._route = value;
  // }

  public toggleConfirmButtonDisplay(eventItemID: number) {
    this.displayConfirmButton = !this.displayConfirmButton;
  }

  public deleteEvent(eventToDelete: number) {
    console.log('deleteEvent\(' + eventToDelete + '\) triggered!');
    this.eventService.removeEvent(eventToDelete).subscribe((response: boolean) => {
      if (response) {
        this.deleted.emit(true);
        console.log('\"response\": \"true\"');
      } else {
        this.UnableToDeleteMessage = 'Unexpected error! Please, contact developers.';
        console.log('UNEXPECTED: \"response\" is not \"true\"!');
      }
    });
  }

  public inscription(activityId: number) {
    this.inscriptionService.getInscription(activityId).subscribe((inscription) => {
      this.connectionError = true;
      this.router.navigate(['/activityInscription']);
    }, () => {
      this.connectionError = false;
    });
  }

  /****************************************************************************************************
  // Info for Zahraa (by Roman):                                                                     //
  // On this commit, I changed the display of the 2 methods below, but didn't actually changed any   //
  // content, so don't worry ;-)                                                                     //
  ****************************************************************************************************/

  // public inscription(id: string) {
  //   if (this.activityError !== null) {
  //     this.activityService.getInscription(id).subscribe((answer: any) => {
  //       this.activity = answer;
  //     });
  //     this.activityError = true;
  //   } else {
  //     this.activityError = false;
  //   }
  // }

  // public inscriptionActivity(idInscriptionActivity: InscriptionActivity) {
  //   if (idInscriptionActivity === null) {
  //     // Apparently nothing yet...
  //   } else {
  //     this.connectionError = false;
  //   }
  // }

}
