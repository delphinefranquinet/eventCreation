import { Injectable } from '@angular/core';

import { Activity } from '../modeles/activity.modele';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class ActivityService {


    constructor(private http: HttpClient) {}

    public getActivity(): Observable<Activity[]> {
      return this.http
        .get<Activity[]>(`${environment.baseUrl}/activity`)
        .pipe(catchError((error: any) => Observable.throw(error.json())));
    }

    public postActivity(activity: Activity): Observable<Activity> {

       return this.http
         .post<Activity>(`${environment.baseUrl}/activity`, activity, { withCredentials: true })
         .pipe(catchError((error: any) => Observable.throw(error.json())));
     }

     public getInscription(id: string): Observable<string> {
      return this.http
        .get<string>(`${environment.baseUrl}/activityInscription/${id}`)
        .pipe(catchError((error: any) => Observable.throw(error.json())));
    }

    public removeActivity(payload: Activity): Observable<Activity> {
      return this.http
        .delete<any>(`${environment.baseUrl}/event/${payload}`)
        .pipe(catchError((error: any) => Observable.throw(error.json())));
    }
}

