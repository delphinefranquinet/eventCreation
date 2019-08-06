import { Component, OnInit, Input } from '@angular/core';

import { Activity } from '../../models/activity.model';
import { InscriptionService } from '../../services/inscription.service';

@Component({
  selector: 'app-activityInscription',
  templateUrl: './activityInscription.component.html',
  styleUrls: ['./activityInscription.component.css']
})
export class ActivityInscriptionComponent implements OnInit {

  // public activityId: number;
  // public activityName: string;
  // public activityStart: Date;
  // public activityEnd: Date;
  // public description: string;
  public InscriptionError: boolean;
  public activityError: boolean;

  @Input()
  public activity: Activity;

  // public activities: Activity[];
  constructor(private inscriptionService: InscriptionService) { }

  ngOnInit() {
    // this.route.params.subscribe(params => {
    //   const id: string = params.id;

    //   this.activityService.postActivityByInscriptionID(id).subscribe((activity: Activity) => {
    //     this.activity = activity;
    //   });
    // });
  }

  public inscription(activityId: number) {
    this.inscriptionService.postInscription(activityId).subscribe(inscription => {
      this.InscriptionError = true;
      console.log('true');
    }, () => {
      this.InscriptionError = false;
      console.log('false');

    });

  }
}

