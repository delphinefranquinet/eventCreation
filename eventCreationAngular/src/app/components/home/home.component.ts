import { Component, OnInit } from '@angular/core';
import { EventService } from '../../../app/services/event.service';
import { EventManage } from '../../models/eventManage.modele';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public events: EventManage[];

  constructor(
    private eventService: EventService
  ) { }

  ngOnInit() {
    this.eventService.getAllEvents().subscribe((events) => {
      this.events = events;
      console.log(JSON.stringify(events));
    });
  }

}
