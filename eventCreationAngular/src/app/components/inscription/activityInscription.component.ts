import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ActivityService } from '../../services/activity.service';
import { Activity } from '../../models/activity.modele';

@Component({
  selector: 'app-activityInscription',
  templateUrl: './activityInscription.component.html',
  styleUrls: ['./activityInscription.component.css']
})
export class ActivityInscriptionComponent implements OnInit {

  public activityName: string;
  public activityStart: Date;
  public activityEnd: Date;
  public activityError: boolean;
  public activity: Activity;
  public descreption: string;

  constructor(private route: ActivatedRoute, private activityService: ActivityService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const id: string = params.id;

      this.activityService.getActivity().subscribe(
        activity => {
          console.log(id);
          // this.activityName = activity.name;
          // this.activityStart = activity.startActivity;
          // this.descreption = activity.description;
          // this.activityEnd = activity.endActivity;
        }
        );
      });

    }

    }

