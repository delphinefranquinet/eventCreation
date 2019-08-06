import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Person } from '../models/person.model';
import { environment } from '../../environments/environment';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ClientSpaceService {

  constructor(private http: HttpClient) { }

  public getPerson(): Observable<Person[]> { // What for? And why at "/home" request? Still useful?
    return this.http
      .get<Person[]>(`${environment.baseUrl}/home`, {withCredentials: true})
      .pipe(catchError((error: any) => throwError(error.error)));
  }

}
