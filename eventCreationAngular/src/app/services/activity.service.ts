import { Injectable } from '@angular/core';
import { Activity } from '../models/activity.modele';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class ActivityService {


  constructor(private http: HttpClient) { }

  public getActivity(): Observable<Activity[]> {
    return this.http
      .get<Activity[]>(`${environment.baseUrl}/activity`)
      // tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.error())));
  }

  public postActivity(activity: Activity): Observable<Activity> {

    return this.http
      .post<Activity>(`${environment.baseUrl}/activity`, activity, { withCredentials: true })
      // tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.error())));
  }

  public getInscription(id: number): Observable<boolean> {
    return this.http
      .get<boolean>(`${environment.baseUrl}/activityInscription/${id}`)
      // tslint:disable-next-line: deprecation
      .pipe(catchError((error: any) => Observable.throw(error.error())));
  }
  /*
      public removeActivity(payload: Activity): Observable<Activity> {
        return this.http
          .delete<any>(`${environment.baseUrl}/event/${payload}`)
          .pipe(catchError((error: any) => Observable.throw(error.error())));
      }

      public updateActivity(payload: Activity): Observable<Activity> {
        return this.http
          .put<Activity>(`${environment.baseUrl}/activity/${payload}`, payload)
          .pipe(catchError((error: any) => Observable.throw(error.error())));
      }*/
}

