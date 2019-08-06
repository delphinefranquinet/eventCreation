import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Activity } from '../models/activity.model';
import { NewActivity } from '../models/newActivity.model';
import { environment } from '../../environments/environment';


@Injectable()
export class ActivityService {


  constructor(private http: HttpClient) { }

  public getActivity(): Observable<Activity[]> {
    return this.http
      .get<Activity[]>(`${environment.baseUrl}/activity`, { withCredentials: true })
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  public createActivity(activity: NewActivity, linkedEvent: number): Observable<Activity> {
    return this.http
      .post<Activity>(`${environment.baseUrl}/activity/${linkedEvent}`, activity, { withCredentials: true })
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  // public postActivityByInscriptionID(id: string): Observable<Activity> {
  //   return this.http
  //     .post<Activity>(`${environment.baseUrl}/activity/${id}`, id, { withCredentials: true })
  //     .pipe(catchError((error: any) => throwError(error.error)));
  // }

  // public getInscription(id: number): Observable<boolean> {
  //   return this.http
  //     .get<boolean>(`${environment.baseUrl}/activityInscription/${id}`)
  //     // tslint:disable-next-line: deprecation
  //     .pipe(catchError((error: any) => throwError(error.error)));
  // }

  public removeActivity(id: number): Observable<boolean> {
    return this.http
      .delete<any>(`${environment.baseUrl}/deleteActivity/${id}`)
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  // public updateActivity(payload: Activity): Observable<Activity> {
  //   return this.http
  //     .put<Activity>(`${environment.baseUrl}/activity/${payload}`, payload)
  //     .pipe(catchError((error: any) => throwError(error.error)));
  // }
}
