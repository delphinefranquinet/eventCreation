import { Injectable } from '@angular/core';
import { HttpClient } from 'selenium-webdriver/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {

constructor(private http: HttpClient) { }

public postInscription(id: string): Observable<string> {
  return this.http
    .post<string>(`${environment.baseUrl}/activityInscription/${id}`)
    .pipe(catchError((error: any) => Observable.throw(error.error())));
}

}
