import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Login } from '../modeles/login.modele';

@Injectable()
export class PersonService {
  constructor(private http: HttpClient) {}

  public getLogin(): Observable<Login[]> {
    return this.http
      .get<Login[]>(`${environment.baseUrl}/logining`)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public createLogin(payload: Login): Observable<Login> {
    return this.http
      .post<Login>(`${environment.baseUrl}/logining`, payload)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public updateLogin(payload: Login): Observable<Login> {
    return this.http
      .put<Login>(`${environment.baseUrl}/logining/${payload.password}`, payload)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

  public removeLogin(payload: Login): Observable<Login> {
    return this.http
      .delete<any>(`${environment.baseUrl}/logining/${payload.login}`)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }
}
