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

  constructor(private eventService: EventService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id: string = params.id;
      console.log('\"typeof params\": ', typeof params);
      console.log('\"JSON.stringify(params)\": ', JSON.stringify(params));
      console.log('\"params.id\": ' + params.id);
      console.log('id of connected user is: ' + id);
      this.eventService.getEventsByIdResponsable(id).subscribe(
        event => this.events = event
      );
    });


    /*this.eventService.removeEvent().subscribe*/

  }
  public deleteEvent(eventToDelete: number) { // penser a avoir un bouton de confirmation
    console.log('deleteEvent\(' + eventToDelete + '\) triggered!');
    this.eventService.removeEvent(eventToDelete).subscribe(response => {
      if (response) {
        console.log('response is \"true\"');
        // signaler a l utilisateur que delete is done ==> faire un refresh
      } else if (!response) {
        console.log('response is \"false\"');
        // signaler textuellement a l utilisateur que delete pas OK
      } else {
        console.log('Error with response format');
        // signaler textuellement a l utilisateur que il y a un probleme a presenter aux developpeurs
      }
    });
  }

}


