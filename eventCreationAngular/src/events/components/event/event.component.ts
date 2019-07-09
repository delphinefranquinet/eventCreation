import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../../app/services/event.service';
import { EventManage } from '../../../app/modeles/eventManage.modele';
import { Activity } from '../../../app/modeles/activity.modele';
import { ActivityService } from '../../../app/services/activity.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

 public eventName: string;
 public eventStart: Date;
 public eventEnd: Date;
 public descreption: string;
 public activities: Activity[];
 public activityError: boolean;
 public activity: Activity;
 public activityName: string;
 public activityStart: Date;
 public activityEnd: Date;
  constructor(private route: ActivatedRoute,private activityService: ActivityService, private eventService: EventService) { }

   ngOnInit() {
      this.route.params.subscribe(params => {
        const id: string = params.id;

        this.eventService.getEventByID(id).subscribe(
          event => {
            this.eventName = event.name;
            this.descreption = event.description;
            this.eventStart = event.startEvent;
            this.eventEnd = event.endEvent;
            this.activities = event.activities;

          }
          );
        });

      }


      public activityExistance(activities: Activity[]){
        if (activities === null) {
          this.activityError = true;
         } else {
           this.activityError = false;
      }
    }
  }
