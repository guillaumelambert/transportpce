import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpRequest } from '../models/http-request.model';

@Injectable({
  providedIn: 'root'
})
export class HttpRequester {
  constructor(
    private http: HttpClient
  ) {}

  invokeRequest(httpRequest: HttpRequest): Observable<any> {
    return this.http.post('/api/invoke', httpRequest);
  }
}
