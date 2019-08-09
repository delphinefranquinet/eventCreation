import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import { Activity } from '../models/activity.model';


@Injectable()
export class ActivityService {
  constructor(
    private http: HttpClient
  ) { }

  public getActivity(): Observable<Activity[]> {
    return this.http
      .get<Activity[]>(`${environment.baseUrl}/activity`, { withCredentials: true })
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  public createActivity(activity: Activity): Observable<Activity> {
    return this.http
      .post<Activity>(`${environment.baseUrl}/activity/${activity.idEvent}`, activity, { withCredentials: true })
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  public removeActivity(id: number): Observable<boolean> {
    return this.http
      .delete<any>(`${environment.baseUrl}/deleteActivity/${id}`)
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  public getInscriptions(): Observable<Activity[]> {
    return this.http
      .get<Activity[]>(`${environment.baseUrl}/allActivityInscription`, { withCredentials: true })
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  // public postActivityByInscriptionID(id: string): Observable<Activity> {
  //   return this.http
  //     .post<Activity>(`${environment.baseUrl}/activity/${id}`, id, { withCredentials: true })
  //     .pipe(catchError((error: any) => throwError(error.error)));
  // }

  // public updateActivity(payload: Activity): Observable<Activity> {
  //   return this.http
  //     .put<Activity>(`${environment.baseUrl}/activity/${payload}`, payload)
  //     .pipe(catchError((error: any) => throwError(error.error)));
  // }
}
