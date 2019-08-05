import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { Login } from '../models/login.modele';

@Injectable()
export class PersonService {
  constructor(private http: HttpClient) {}

  public getLogin(): Observable<Login[]> {
    return this.http
      .get<Login[]>(`${environment.baseUrl}/something`)
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  public createLogin(payload: Login): Observable<Login> {
    return this.http
      .post<Login>(`${environment.baseUrl}/something`, payload)
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  /*public updateLogin(payload: Login): Observable<Login> {
    return this.http
      .put<Login>(`${environment.baseUrl}/something/${payload.password}`, payload)
      .pipe(catchError((error: any) => throwError(error.error)));
  }

  public removeLogin(payload: Login): Observable<Login> {
    return this.http
      .delete<any>(`${environment.baseUrl}/something/${payload.email}`)
      .pipe(catchError((error: any) => throwError(error.error)));
  }*/
}
