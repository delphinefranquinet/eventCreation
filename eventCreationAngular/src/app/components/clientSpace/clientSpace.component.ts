import { Component, OnInit } from '@angular/core';
import { EventService } from '../../services/event.service';
import { EventManage } from '../../models/eventManage.modele';
import { ActivatedRoute } from '@angular/router';
import { Activity } from '../../models/activity.modele';
import { runInThisContext } from 'vm';

@Component({
  selector: 'app-clientSpace',
  templateUrl: './clientSpace.component.html',
  styleUrls: ['./clientSpace.component.css']
})
export class ClientSpaceComponent implements OnInit {

 public events: EventManage[];
 public event: EventManage;

  constructor(private eventService: EventService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id: string = params.id;
      this.eventService.getEventsByIdResponsable(id).subscribe(
      event =>  this.events = event
        );
      });


    this.eventService.removeEvent().subscribe

  }

}


