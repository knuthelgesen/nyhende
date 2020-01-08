import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ContentType} from "../model/content.model";

@Injectable({
  providedIn: 'root'
})
export class ContentService {

  private baseApiUrl: string = '/api/content'

  constructor(private http: HttpClient) {
  }

  getContentType(url: string): Observable<ContentType> {
    return this.http.get<ContentType>(this.baseApiUrl + '?url=' + url).pipe();
  }

}
