import { Component, OnInit } from '@angular/core';
import { EventService } from '../../services/event.service';
import { EventManage } from '../../models/eventManage.modele';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-clientSpace',
  templateUrl: './clientSpace.component.html',
  styleUrls: ['./clientSpace.component.css']
})
export class ClientSpaceComponent implements OnInit {

  public events: EventManage[];
  public event: EventManage;
  private connectedUserID: number;

  constructor(private eventService: EventService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.connectedUserID = Number(params.id);
      console.log('\"typeof params\": ', typeof params);
      console.log('\"JSON.stringify(params)\": ', JSON.stringify(params));
      console.log('\"params.id\": ' + params.id);
      console.log('id of connected user is: ' + this.connectedUserID);
    });
    this.updateEvents();
  }

  private updateEvents(): void {
    this.eventService.getEventsByIdResponsable(this.connectedUserID).subscribe((events) => {
      this.events = events;
    });
  }

  public deleteEvent(eventToDelete: number) { // penser a avoir un bouton de confirmation
    console.log('deleteEvent\(' + eventToDelete + '\) triggered!');
    this.eventService.removeEvent(eventToDelete).subscribe(response => {
      if (response) {
        this.updateEvents();
        console.log('response is \"true\"');
      } else if (!response) { // signaler textuellement a l utilisateur que delete pas OK
        console.log('response is \"false\"');
      } else { // signaler textuellement a l utilisateur que il y a un probleme a presenter aux developpeurs
        console.log('Error with response format');
      }
    });
  }

}


