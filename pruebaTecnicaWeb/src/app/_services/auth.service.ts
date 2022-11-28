import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IUser } from '../pages/interfaces/user';

/**
 * Servicio encargado de validar la autenticidad del usuario logueado.
 */
const AUTH_API = '/api/auth';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }


  login(username: string, password: string): Observable<any> {

    let user: IUser;

    user = {
      username: username,
      password: password,
      firstName: '',
      lastName: '',
      session: ''
    }
    return this.http.post(
      AUTH_API,
      user,
      httpOptions
    );
  }


}
