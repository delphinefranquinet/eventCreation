import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { EventManage } from '../models/eventManage.model';
import { EventsAndPersons } from '../models/EventsAndPersons.model';

@Injectable()
export class EventService {
  constructor(private http: HttpClient) {
    //
  }


  public postEvent(event: EventManage): Observable<EventManage> {
    return this.http
    .post<EventManage>(`${environment.baseUrl}/createEvent`, event, { withCredentials: true })
    .pipe(catchError((error: any) => throwError(error.error)));
  }

  public getEventsAndPersons(): Observable<EventsAndPersons[]> {
    return this.http
    .get<EventsAndPersons[]>(`${environment.baseUrl}/home`)
    .pipe(catchError((error: any) => throwError(error.error)));
  }


  public getAllEvents(): Observable<any> { // Back-end returns an EventsAndPersons object instead of an EventManage
    return this.http
    .get<EventManage[]>(`${environment.baseUrl}/home`)
    .pipe(catchError((error: any) => throwError(error.error)));
  }

  public getEventByID(id: number): Observable<EventManage> {
    return this.http
    .get<EventManage>(`${environment.baseUrl}/event/${id}`)
    .pipe(catchError((error: any) => throwError(error.error)));
  }

  public getEventsByIdResponsable(): Observable<EventManage[]> {
    return this.http
    .get<EventManage[]>(`${environment.baseUrl}/clientSpace`, {withCredentials: true})
    .pipe(catchError((error: any) => throwError(error.error)));
  }

  public removeEvent(id: number): Observable<boolean> {
    return this.http
    .delete<any>(`${environment.baseUrl}/deleteEvent/${id}`)
    .pipe(catchError((error: any) => throwError(error.error)));
  }

  public updateEvent(eventToUpdate: EventManage): Observable<boolean> {
    return this.http
      .put<boolean>(`${environment.baseUrl}/updateEvent`, eventToUpdate, { withCredentials: true })
      .pipe(catchError((error: any) => throwError(error.error)));
  }


}
