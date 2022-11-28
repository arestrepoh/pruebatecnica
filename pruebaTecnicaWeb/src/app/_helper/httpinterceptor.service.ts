import { HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, retry } from 'rxjs';
import { StorageService } from '../_services/storage.service';

@Injectable({
  providedIn: 'root'
})

/**
 * Intercepta las peticiones del navegador
 */
export class HttpinterceptorService implements HttpInterceptor {

  constructor(private storageService: StorageService) { }

  /**
   * Interceptor de las peticiones http
   * @param req 
   * @param next 
   * @returns 
   */
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = this.storageService.getToken();

    if (token) {
      req = req.clone({
        setHeaders: {
          authorization: token
        }
      });
    }
    req = req.clone({
      withCredentials: true
    });
    return next.handle(req);
  }

}

/**
 * Exportacion de constante
 */
export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: HttpinterceptorService, multi: true },
];


