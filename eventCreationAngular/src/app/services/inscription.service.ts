import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InscriptionService {

  constructor(private http: HttpClient) { }


public postInscription(id: number): Observable<boolean> {
  return this.http.post<boolean>(`${environment.baseUrl}/activityInscription/${id}`, id, {withCredentials: true})
    .pipe(catchError((error: any) => throwError(error.error)));
}

public deleteInscription(id: number): Observable<boolean>{
  return this.http
    .delete<any>(`${environment.baseUrl}/deleteInscription/${id}`, {withCredentials: true})
    .pipe(catchError((error: any) => throwError(error.error)));
}

}
