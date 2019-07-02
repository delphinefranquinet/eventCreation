import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Login } from '../modeles/login.modele';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {}

  public isLogin(login: Login): Observable<Login> {
    return this.http
      .post<Login>(`${environment.baseUrl}/logining`, login)
      .pipe(catchError((error: any) => Observable.throw(error.json())));
  }

}
