import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MenuItem} from "../model/menu.model";

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  private baseApiUrl: string = '/api/menu'

  constructor(private http: HttpClient) { }

  getMenu(): Observable<MenuItem[]> {
    return this.http.get<MenuItem[]>(this.baseApiUrl).pipe();
  }

}
