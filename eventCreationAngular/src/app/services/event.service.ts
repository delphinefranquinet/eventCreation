import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { EventManage } from '../models/eventManage.modele';

@Injectable()
export class EventService {
  constructor(private http: HttpClient) {}


  public postEvent(event: EventManage): Observable<EventManage> {

     return this.http
       .post<EventManage>(`${environment.baseUrl}/createEvent`, event, { withCredentials: true })
       .pipe(catchError((error: any) => Observable.throw(error.error())));
   }

  public getEvent(): Observable<EventManage[]> {
    return this.http
      .get<EventManage[]>(`${environment.baseUrl}/home`)
      .pipe(catchError((error: any) => Observable.throw(error.error())));
  }

  public getEventByID(id: string): Observable<EventManage> {
    return this.http
      .get<EventManage>(`${environment.baseUrl}/event/${id}`)
      .pipe(catchError((error: any) => Observable.throw(error.error())));
  }

  public getEventsByIdResponsable(id: string): Observable<EventManage[]> {
    return this.http
      .get<EventManage[]>(`${environment.baseUrl}/clientSpace/${id}`)
      .pipe(catchError((error: any) => Observable.throw(error.error())));
  }

  public removeEvent(eventToDelete: EventManage): Observable<EventManage> {
    return this.http
      .delete<any>(`${environment.baseUrl}/event/${eventToDelete.id}`)
      .pipe(catchError((error: any) => Observable.throw(error.error())));
  }

/*
  public updateEvent(eventToUpdate: Event): Observable<Event> {
    return this.http
      .put<Event>(`${environment.baseUrl}/contacts/${eventToUpdate}`, eventToUpdate)
      .pipe(catchError((error: any) => Observable.throw(error.error())));
  }

 */
}
