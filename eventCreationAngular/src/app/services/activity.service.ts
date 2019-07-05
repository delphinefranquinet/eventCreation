import { Injectable } from '@angular/core';

import { Activity } from '../modeles/activity.modele';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class ActivityService {


    constructor(private http: HttpClient) {}

    public postActivity(event: Activity): Observable<Activity> {

       return this.http
         .post<Activity>(`${environment.baseUrl}/activity`, event, { withCredentials: true })
         .pipe(catchError((error: any) => Observable.throw(error.json())));
     }


}

