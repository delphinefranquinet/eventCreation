import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Login } from '../models/login.model';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { Person } from '../models/person.model';
import { throwError } from 'rxjs';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) { }

  public postLogin(email: Login): Observable<Person> {
    return this.http
      .post<Person>(`${environment.baseUrl}/login`, email, { withCredentials: true })
      .pipe(catchError((error: any) => throwError(error.error)));
  }

}
