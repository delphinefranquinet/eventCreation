import { Component, OnInit } from '@angular/core';
import { EventService } from '../../../app/services/event.service';
import { EventManage } from '../../models/eventManage.modele';
import { ClientSpaceService } from '../../services/clientSpace.service';
import { Person } from '../../models/person.modele';
import { EventsAndPersons } from '../../models/EventsAndPersons';
import { ActivatedRoute, Router } from '@angular/router';
import { Login } from '../../models/login.modele';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public eventAndPerson: EventsAndPersons = new EventsAndPersons();
  public connectionError: boolean;
  public logins: Login;

  constructor(private router: Router,
    // tslint:disable-next-line: align
    private eventService: EventService, private clientSpaceService: ClientSpaceService, private route: ActivatedRoute) { }

  ngOnInit() {

    this.eventService.getEventsAndPersons().subscribe((eventAndPerson: any) => {
      this.eventAndPerson.events = eventAndPerson.events;
      this.eventAndPerson.persons = eventAndPerson.persons;
    });


    // this.clientSpaceService.getPerson().subscribe(persons => {
    //   this.eventAndPerson.persons = persons.persons;
    //   console.log(JSON.stringify(persons.persons));
    //   console.log(this.eventAndPerson.persons[0].id);
    // });

  }
}
