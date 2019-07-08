import { Component, OnInit } from '@angular/core';
import { EventService } from '../../../app/services/event.service';
import { EventManage } from '../../../app/modeles/eventManage.modele';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public event: EventManage;
  public events: EventManage[];

  constructor( private eventService: EventService) { }

  ngOnInit() {
   this.eventService.getEvent().subscribe(events => this.events = events);
  }

}
