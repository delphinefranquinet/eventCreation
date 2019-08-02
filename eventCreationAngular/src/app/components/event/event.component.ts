import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Activity } from '../../models/activity.modele';
import { ActivityService } from '../../services/activity.service';
import { InscriptionActivity } from '../../models/inscriptionActivity.modele';
import { InscriptionService } from '../../services/inscription.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  public eventId: number;
  public eventName: string;
  public eventStart: Date;
  public eventEnd: Date;
  public descreption: string;
  public eventPlace: string;
  public activities: Activity[];
  public activityError: boolean;
  public activity: Activity;
  public connectionError: boolean;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private inscriptionService: InscriptionService,
              private eventService: EventService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id: string = params.id;

      this.eventService.getEventByID(id).subscribe(
        event => {
          this.eventId = event.id;
          this.eventName = event.name;
          this.descreption = event.description;
          this.eventPlace = event.place;
          this.eventStart = event.startEvent;
          this.eventEnd = event.endEvent;
          this.activities = event.activities;

        }
      );
    });

  }
  public inscription(activityId: number) {
    this.inscriptionService.getInscription(activityId).subscribe(inscription => {
      this.connectionError = true;
      this.router.navigate(['/activityInscription']);
    }, () => {
      this.connectionError = false;
    });


  }

  // public inscriptionActivity(idInscriptionActivity: InscriptionActivity) {
  //   if (idInscriptionActivity === null) {

  //   } else {
  //     this.connectionError = false;

  //   }
  // }
  /*
      public inscription(id: string) {
        if ( this.activityError !== null) {
        this.activityService.getInscription(id).subscribe(answer => this.activity = answer);

          this.activityError = true;
         } else {
           this.activityError = false;

         }}*/
}
