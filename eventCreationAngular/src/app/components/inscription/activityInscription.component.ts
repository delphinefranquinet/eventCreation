import { Component, OnInit, Input } from '@angular/core';

import { Activity } from '../../models/activity.model';
import { InscriptionService } from '../../services/inscription.service';

@Component({
  selector: 'app-activityInscription',
  templateUrl: './activityInscription.component.html',
  styleUrls: ['./activityInscription.component.css']
})
export class ActivityInscriptionComponent implements OnInit {

  public InscriptionError = true;
  public InscriptionOk = false;

  @Input()
  public activity: Activity;

  // public activities: Activity[];
  constructor(
    private inscriptionService: InscriptionService
  ) { }

  ngOnInit() {
    // this.route.params.subscribe(params => {
    //   const id: string = params.id;

    //   this.activityService.postActivityByInscriptionID(id).subscribe((activity: Activity) => {
    //     this.activity = activity;
    //   });
    // });
  }

  public inscription(activityId: number) {
    this.inscriptionService.postInscription(activityId).subscribe((inscription: boolean) => {
      if (inscription === true) {
        this.InscriptionError = false;
        this.InscriptionOk = false;
        console.log('inscription ok');
      } else {
        this.InscriptionOk = true;
      }

    }, (banana: any) => {

      this.InscriptionError = true;
      this.InscriptionOk = true;
      console.log('inscription not ok');
      console.log(typeof banana);
      console.log(banana);
      console.log(JSON.stringify(banana));
    });

  }
}


