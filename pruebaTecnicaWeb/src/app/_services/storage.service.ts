import { Injectable } from '@angular/core';
import { IUser } from '../pages/interfaces/user';

const USER_KEY = 'auth-user';
const TOKEN_KEY = 'token';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  /**
   * Limpiar el almacenamiento de la sesion.
   */
  clean(): void {
    window.sessionStorage.clear();
  }

  /**
   * Almacena la informacion del usuario loggueado.
   * @param user 
   */
  saveUser(user: IUser): void {
    window.sessionStorage.removeItem(USER_KEY);

    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    window.sessionStorage.setItem(TOKEN_KEY, user.session);
  }

  getToken() {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  /**
   * Obtiene la informacion del usuario.
   * @returns 
   */
  getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  /**
   * Indica si ya el usuario esta loggueado.
   * @returns 
   */
  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    const token = window.sessionStorage.getItem(TOKEN_KEY);
    if (user && token) {
      return true;
    }
    this.clean();
    return false;
  }
}
