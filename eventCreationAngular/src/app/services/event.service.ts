import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { EventManage } from '../modeles/eventManage.modele';

@Injectable()
export class EventService {
  constructor(private http: HttpClient) {}


  public postEvent(event: EventManage): Observable<EventManage> {

     return this.http
       .post<EventManage>(`${environment.baseUrl}/createEvent`, event, { withCredentials: true })
       .pipe(catchError((error: any) => Observable.throw(error.json())));
   }
  public getEvent(): Observable<EventManage[]> {
    return this.http
      .get<EventManage[]>(`${environment.baseUrl}/home`)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }
  public getEventByID(id: string): Observable<EventManage> {
    return this.http
      .get<EventManage>(`${environment.baseUrl}/event/${id}`)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }
/*
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
  }*/
}
