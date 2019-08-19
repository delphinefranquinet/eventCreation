import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { EventManage } from '../../models/eventManage.model';
import { EventService } from '../../services/event.service';

@Component({
  selector: 'app-showActivity',
  templateUrl: './showActivity.component.html',
  styleUrls: ['./showActivity.component.css']
})

export class ShowActivityComponent implements OnInit {

  public eventItem = new EventManage();
  public showActivityComponentIsReady = false;

  constructor(
    private eventService: EventService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.eventItem.id = Number(params.id);
      this.updateAll(false);
      this.showActivityComponentIsReady = true;
    });
  }

  public updateAll(isDeleted: boolean): void {
    this.eventService.getEventByID(this.eventItem.id).subscribe((selectedEvent: EventManage) => {
      this.eventItem = selectedEvent;
      if (this.eventItem.id === null) {
        this.router.navigate(['/clientSpace']);
      }
    });
  }

}
