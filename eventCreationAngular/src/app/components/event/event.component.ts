import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Activity } from '../../models/activity.modele';
import { ActivityService } from '../../services/activity.service';
import { InscriptionService } from '../../services/inscription.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {
  public get route(): ActivatedRoute {
    return this._route;
  }
  public set route(value: ActivatedRoute) {
    this._route = value;
  }

  @Input()
  public editMode = false; // true if and only if parent component tells so

  public eventId: number;
  public eventName: string;
  public eventStart: Date;
  public eventEnd: Date;
  public eventDescreption: string;
  public eventPlace: string;
  public activities: Activity[];
  public activityError: boolean;
  public activity: Activity;
  public connectionError: boolean;
  public activityDescreption: string;
  public activityId: number;
  public activityName: string;
  public activityStart: Date;
  public activityEnd: Date;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private inscriptionService: InscriptionService,
    private activityService: ActivityService,
    private eventService: EventService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id: string = params.id;
      this.eventService.getEventByID(id).subscribe((event) => {
        this.eventId = event.id;
        this.eventName = event.name;
        this.eventDescreption = event.description;
        this.eventPlace = event.place;
        this.eventStart = event.startEvent;
        this.eventEnd = event.endEvent;
        this.activities = event.activities;
      });
    });
    this.route.params.subscribe((params) => {
      const id: string = params.id;
      this.activityService.postActivityByInscriptionID(id).subscribe((activity) => {
          // this.activity = activity;
          this.activityId = activity.id;
          this.activityName = activity.name;
          this.activityDescreption = activity.description;
          this.activityStart = activity.startActivity;
          this.activityEnd = activity.endActivity;
        });
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
