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

  public getActivities(): Observable<Activity[]> {
    return this.http
      .get<Activity[]>(`${environment.baseUrl}/activity`, { withCredentials: true })
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  public getOneActivityById(id: number): Observable<Activity> {
    return this.http
      .get<Activity>(`${environment.baseUrl}/oneActivity/${id}`)
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

  public updateActivity(activity: Activity): Observable<Activity> {
    return this.http
      .put<Activity>(`${environment.baseUrl}/updateOneActivity/${activity.id}`, activity)
      .pipe(catchError((error: any) => throwError(error.error)));
    // TODO: Attention que dans le back-end, pour cette requette et probablement pour d'autres,
    // l'action s'effectue sans vérifier que les Credentitials sont corrects
  }

  // public postActivityByInscriptionID(id: string): Observable<Activity> {
  //   return this.http
  //     .post<Activity>(`${environment.baseUrl}/activity/${id}`, id, { withCredentials: true })
  //     .pipe(catchError((error: any) => throwError(error.error)));
  // }
}
