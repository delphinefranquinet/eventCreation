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

  private connectedUserID: number;
  public events: EventManage[];

  constructor(private eventService: EventService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.connectedUserID = Number(params.id);
      console.log('\"typeof params\": ', typeof params);
      console.log('\"JSON.stringify(params)\": ', JSON.stringify(params));
      console.log('\"params.id\": ' + params.id);
      console.log('id of connected user is: ' + this.connectedUserID);
    });
    this.updateAll(false);
  }

  public updateAll(isDeleted: boolean): void {
    this.eventService.getEventsByIdResponsable(this.connectedUserID).subscribe((updatedEvents) => {
      this.events = updatedEvents;
    });
  }

}
