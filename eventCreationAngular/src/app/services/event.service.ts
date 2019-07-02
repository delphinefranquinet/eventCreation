import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Contact } from '../modeles/contact.modele';
import { Observable } from 'rxjs';

@Injectable()
export class ContactService {
  constructor(private http: HttpClient) {}

  public getContact(): Observable<Contact[]> {
    return this.http
      .get<Contact[]>(`${environment.baseUrl}/contacts`)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public createContact(payload: Contact): Observable<Contact> {
    return this.http
      .post<Contact>(`${environment.baseUrl}/contacts`, payload)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public updateContact(payload: Contact): Observable<Contact> {
    return this.http
      .put<Contact>(`${environment.baseUrl}/contacts/${payload.id}`, payload)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public removeContact(payload: Contact): Observable<Contact> {
    return this.http
      .delete<any>(`${environment.baseUrl}/contacts/${payload.id}`)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }
}
