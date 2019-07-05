import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Register } from '../modeles/register.modele';


@Injectable()
export class RegisterService {

    constructor(private http: HttpClient) {}

     public postActivity(event: Register): Observable<Register> {

       return this.http
         .post<Register>(`${environment.baseUrl}/register`, event, { withCredentials: true })
         .pipe(catchError((error: any) => Observable.throw(error.json())));
     }

}
