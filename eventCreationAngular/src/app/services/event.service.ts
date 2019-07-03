import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable()
export class EventService {
  constructor(private http: HttpClient) {}

  public getEvent(): Observable<Event[]> {
    return this.http
      .get<Event[]>(`${environment.baseUrl}/events`)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public createEvent(payload: Event): Observable<Event> {
    return this.http
      .post<Event>(`${environment.baseUrl}/events`, payload)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public updateEvent(payload: Event): Observable<Event> {
    return this.http
      .put<Event>(`${environment.baseUrl}/contacts/${payload}`, payload)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public removeEvent(payload: Event): Observable<Event> {
    return this.http
      .delete<any>(`${environment.baseUrl}/contacts/${payload}`)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }
}