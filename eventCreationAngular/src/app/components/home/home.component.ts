import { Component, OnInit } from '@angular/core';
import { EventService } from '../../../app/services/event.service';
import { EventManage } from 'src/app/models/eventManage.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public events: EventManage[];
  constructor(private eventService: EventService) { }

  ngOnInit() {
    this.eventService.getAllEvents().subscribe((eventsAndPersons: any) => {
      this.events = eventsAndPersons.events;
      console.log(JSON.stringify(eventsAndPersons));
    });
  }

  debug(activity: any) {
    console.log(activity);
  }

}
