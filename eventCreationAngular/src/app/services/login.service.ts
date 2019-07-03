import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Login } from '../modeles/login.modele';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { Person } from '../modeles/person.modele';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) {}

  public postLogin(login: Login): Observable<Person> {

   /* const httpHeaders = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Cache-Control': 'no-cache',
      'Access-Control-Allow-Origin' : '*',
    });

    const options = {
      headers: httpHeaders
    };
*/
    return this.http
      .post<Person>(`${environment.baseUrl}/login`, login)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

}
