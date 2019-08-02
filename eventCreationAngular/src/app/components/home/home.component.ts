import { Component, OnInit } from '@angular/core';
import { EventService } from '../../../app/services/event.service';
import { EventManage } from '../../models/eventManage.modele';
import { ClientSpaceService } from '../../services/clientSpace.service';
import { Person } from '../../models/person.modele';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public events: EventManage[];
  public persons: Person[];

  constructor(private eventService: EventService, private clientSpaceService: ClientSpaceService) { }

  ngOnInit() {
    this.eventService.getEvent().subscribe(events => this.events = events);
    this.clientSpaceService.getPerson().subscribe(persons => this.persons = persons);
  }

}
