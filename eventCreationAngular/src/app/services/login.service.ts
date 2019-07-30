import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Login } from '../models/login.modele';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { Person } from '../models/person.modele';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) { }

  public postLogin(login: Login): Observable<Person> {
    return this.http
      .post<Person>(`${environment.baseUrl}/login`, login, { withCredentials: true });
  }

}
