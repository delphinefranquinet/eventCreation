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

  public event: EventManage;
  public activity: Activity;
  constructor(private route: ActivatedRoute, private eventService: EventService) { }

   ngOnInit() {
      this.route.params.subscribe(params => {
        const id: string = params.id;
        console.log(id);
        this.eventService.getEventByID(id).subscribe(
          event => {

            this.event = event;
          }
          );
       });

 

      }
}
