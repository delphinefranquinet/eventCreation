import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ActivityService } from '../../services/activity.service';
import { Activity } from '../../models/activity.model';
import { InscriptionService } from '../../services/inscription.service';

@Component({
  selector: 'app-activityInscription',
  templateUrl: './activityInscription.component.html',
  styleUrls: ['./activityInscription.component.css']
})
export class ActivityInscriptionComponent implements OnInit {

  public activityId: number;
  public activityName: string;
  public activityStart: Date;
  public activityEnd: Date;
  public activityError: boolean;
  public activity: Activity;
  public descreption: string;
  public connectionError: boolean;
  // public activities: Activity[];
  constructor(private route: ActivatedRoute, private inscriptionService: InscriptionService, private activityService: ActivityService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id: string = params.id;

      this.activityService.postActivityByInscriptionID(id).subscribe(
        activity => {
          // this.activity = activity;

          this.activityId = activity.id;
          this.activityName = activity.name;
          this.descreption = activity.description;
          this.activityStart = activity.startActivity;
          this.activityEnd = activity.endActivity;


        }
      );
    });


    }
    public inscription(activityId: number) {
      this.inscriptionService.getInscription(activityId).subscribe(inscription => {
        this.connectionError = true;
      }, () => {
      this.connectionError = false;
      });

    }
  }

