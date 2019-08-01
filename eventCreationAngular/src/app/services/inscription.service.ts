import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {

  constructor(private http: HttpClient) { }


public getInscription(id: number): Observable<boolean> {
  return this.http.get<boolean>(`${environment.baseUrl}/activityInscription/${id}`)
    .pipe(catchError((error: any) => Observable.throw(error.error())));
}

}
