import { Component, OnInit, Input } from '@angular/core';
import { EventManage } from '../../models/eventManage.model';
import { Activity } from '../../models/activity.model';
import { ActivatedRoute } from '@angular/router';
import { EventService } from '../../services/event.service';

@Component({
  selector: 'app-showActivity',
  templateUrl: './showActivity.component.html',
  styleUrls: ['./showActivity.component.css']
})

export class ShowActivityComponent implements OnInit {

  public event: EventManage;
  public activities: Activity[];
  private eventID: number;

  @Input()
  public eventId = -1;

  constructor(
    private eventService: EventService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.eventID = Number(params.id);
      this.updateAll(false);
      // TODO: recup ca via une request au back (get idPerson from session ou un truc du genre)!
    });
  }

  public updateAll(isDeleted: boolean): void {
    this.eventService.getEventByID(this.eventID).subscribe((selectedEvent) => {
      this.event = selectedEvent;
    });
  }
}
