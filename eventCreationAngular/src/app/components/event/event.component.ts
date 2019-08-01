import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { EventManage } from '../../models/eventManage.modele';
import { Activity } from '../../models/activity.modele';
import { ActivityService } from '../../services/activity.service';

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

  constructor(private route: ActivatedRoute, private activityService: ActivityService, private eventService: EventService) { }

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

/*
    public inscription(id: string) {
      if ( this.activityError !== null) {
      this.activityService.getInscription(id).subscribe(answer => this.activity = answer);

        this.activityError = true;
       } else {
         this.activityError = false;

       }}*/
    }
