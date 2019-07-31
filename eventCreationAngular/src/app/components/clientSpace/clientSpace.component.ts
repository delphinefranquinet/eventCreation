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
      // this.eventService.getEventsByIdResponsable(id).subscribe( // To un comment when done...
      this.eventService.getEvent().subscribe( // To delete when done...
        event => this.events = event
      );
    });


    /*this.eventService.removeEvent().subscribe*/

  }
  public deleteEvent(eventToDelete: number) {
    console.log('deleteEvent\(' + eventToDelete + '\) triggered!');
    this.eventService.removeEvent(eventToDelete).subscribe(response => {
      console.log('Response, typeof response, response.toString(), JSON.stringify(response): ',
        response, typeof response, response.toString(), JSON.stringify(response));
      if (JSON.stringify(response) === '{\"deleted\": true}') {
        // dire que delete is done
      } else if (JSON.stringify(response) === '{\"deleted\": false}') {
        // dire que delete pas OK
      } else {
        console.log('Error with response format');
      }
    });
  }
}


