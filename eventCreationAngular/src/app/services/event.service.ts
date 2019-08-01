import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { EventManage } from '../models/eventManage.modele';

@Injectable()
export class EventService {
  constructor(private http: HttpClient) {}


  public postEvent(event: EventManage): Observable<EventManage> {

     return this.http
       .post<EventManage>(`${environment.baseUrl}/createEvent`, event, { withCredentials: true })
       .pipe(catchError((error: any) => throwError(error.error)));
   }

  public getEvent(): Observable<EventManage[]> {
    return this.http
      .get<EventManage[]>(`${environment.baseUrl}/home`)
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  public getEventByID(id: string): Observable<EventManage> {
    return this.http
      .get<EventManage>(`${environment.baseUrl}/event/${id}`)
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  public getEventsByIdResponsable(id: string): Observable<EventManage[]> {
    return this.http
      .get<EventManage[]>(`${environment.baseUrl}/clientSpace/${id}`)
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  public removeEvent(idOfEventToDelete: number): Observable<boolean> {
    return this.http
      .delete<any>(`${environment.baseUrl}/deleteEvent/${idOfEventToDelete}`)
      .pipe(catchError((error: any) => throwError(error.error)));
  }

/*
  public updateEvent(eventToUpdate: Event): Observable<Event> {
    return this.http
      .put<Event>(`${environment.baseUrl}/contacts/${eventToUpdate}`, eventToUpdate)
      .pipe(catchError((error: any) => throwError(error.error)));
  }

 */
}
