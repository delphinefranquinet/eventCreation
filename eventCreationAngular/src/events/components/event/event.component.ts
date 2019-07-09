import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../../app/services/event.service';
import { EventManage } from '../../../app/modeles/eventManage.modele';
import { Activity } from '../../../app/modeles/activity.modele';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

 public eventName: string;
 public eventStart: Date;
 public eventEnd: Date;

  public activities: Activity[];

  constructor(private route: ActivatedRoute, private eventService: EventService) { }

   ngOnInit() {
      this.route.params.subscribe(params => {
        const id: string = params.id;

        this.eventService.getEventByID(id).subscribe(
          event => {
            this.eventName = event.name;
            console.log( this.eventName);
            this.eventStart = event.startEvent;
            this.eventEnd = event.endEvent;

          }
          );
        });

      }
      }

